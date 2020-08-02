package com.jz.jsample;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

/**
 * @author zhange
 * @date 2019-11-04.
 * description：通用 dialog
 */
public class CommonDialog extends Dialog {
    TextView title;
    TextView content;
    TextView cancel;
    TextView confirm;

    private Builder mBuilder;
    private Builder.OnClickListener mListener;

    public CommonDialog(Builder builder) throws NullPointerException {
        super(builder.mContext);
        this.mBuilder = builder;
        initViews();
    }

    public static class Builder {
        private Context mContext;
        private CharSequence mTitle = "";
        private CharSequence mContent = "";
        private String mCancel = "Cancel";
        private String mConfirm = "Confirm";
        private OnClickListener mListener;
        // 是否有取消按钮
        private boolean mCancelBtn = true;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(CharSequence title) {
            mTitle = title;
            return this;
        }

        public Builder setContent(CharSequence content) {
            mContent = content;
            return this;
        }

        public Builder setCancel(String cancel) {
            mCancel = cancel;
            return this;
        }

        public Builder setConfirm(String confirm) {
            mConfirm = confirm;
            return this;
        }

        public Builder setListener(OnClickListener listener) {
            mListener = listener;
            return this;
        }

        public Builder setCancelBtnVisible(boolean flag) {
            this.mCancelBtn = flag;
            return this;
        }

        public CommonDialog build() {
            return new CommonDialog(this);
        }

        public interface OnClickListener {
            void onConfirm(CommonDialog dialog);

            void onCancel();
        }
    }

    private void initViews() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = getLayoutInflater().inflate(R.layout.app_dialog_common, null);
        setContentView(view);

        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
        cancel = view.findViewById(R.id.cancel);
        confirm = view.findViewById(R.id.confirm);
        title.setText(mBuilder.mTitle);
        content.setText(mBuilder.mContent);
        cancel.setText(mBuilder.mCancel);
        confirm.setText(mBuilder.mConfirm);
        cancel.setVisibility(mBuilder.mCancelBtn ? View.VISIBLE : View.GONE);
        mListener = mBuilder.mListener;
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mListener != null) {
                    mListener.onConfirm(CommonDialog.this);
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mListener != null) {
                    mListener.onCancel();
                }
            }
        });

        Window window = getWindow();
        if (window != null) {
            int i = (int) (mBuilder.mContext.getResources().getDisplayMetrics().density * 278 + 0.5f);
            window.setLayout(i, ViewGroup.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            setCanceledOnTouchOutside(false);
            setCancelable(true);
        } else {
            throw new NullPointerException("window not fount exception!");
        }
    }

    public void clear() {
        if (mListener != null) mListener = null;
        if (mBuilder != null && mBuilder.mContext != null) {
            mBuilder.mContext = null;
            mBuilder = null;
        }
    }


    public static int dip2px(Context context, float dip) {
        if (context == null) {
            return dip2px(1.5f, dip);
        }
        return dip2px(context.getResources().getDisplayMetrics().density, dip);
    }

    public static int dip2px(float scale, float dip) {
        return (int) (dip * scale + 0.5f);
    }
}
