package com.example.stepperlib;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

public class Stepper extends RelativeLayout {

    private int stepCount = 2;
    private int currentStepCount = 1;
    private long defaultDuration = 500;
    private int screenWidth = 0;
    private boolean isRunning = false;
    private boolean inProgress = false;
    private Runnable completeListener;
    private ValueAnimator progressValueAnimator;
    private Animator.AnimatorListener progressAnimationListener;

    public Stepper(Context context) {
        this(context, null);
    }

    public Stepper(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Stepper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.Stepper, defStyleAttr, 0);
        stepCount = attr.getInt(R.styleable.Stepper_stepCount, 5);
        defaultDuration = attr.getInt(R.styleable.Stepper_duration, 500);
        attr.recycle();

        post(new Runnable() {
            @Override
            public void run() {
                if (getChildCount() != 1) {
                    throw new IllegalStateException("Stepper must have only one child layout");
                }

                screenWidth = getResources().getDisplayMetrics().widthPixels;
                getChildAt(0).setLayoutParams(new LayoutParams(screenWidth / stepCount, getChildAt(0).getHeight()));
            }
        });
    }

    public int getStepCount() {
        return stepCount;
    }

    public void setStepCount(int stepCount) {
        this.stepCount = stepCount;
    }

    public long getDefaultDuration() {
        return defaultDuration;
    }

    public void setDefaultDuration(long defaultDuration) {
        this.defaultDuration = defaultDuration;
    }

    public void addOnCompleteListener(Runnable addOnCompleteListener) {
        this.completeListener = addOnCompleteListener;
    }

    private ValueAnimator getValueAnimator(ValueAnimator animator) {
        final int[] leftOffset = {1}; // Declare as array to make it effectively final

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (int) valueAnimator.getAnimatedValue();
                LayoutParams layoutParams = (LayoutParams) getChildAt(0).getLayoutParams();
                layoutParams.width = val;
                getChildAt(0).setLayoutParams(layoutParams);
            }
        });

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationRepeat(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation) {
                isRunning = !isRunning;
                if (completeListener != null)
                    completeListener.run();
            }

            @Override
            public void onAnimationCancel(Animator animation) {}

            @Override
            public void onAnimationStart(Animator animation) {
                isRunning = !isRunning;
            }
        });

        animator.setDuration(defaultDuration);
        return animator;
    }

    public void forward() {
        if (inProgress) {
            stop();
        }
        if (!isRunning) {
            currentStepCount++;
            getValueAnimator(ValueAnimator.ofInt(
                    getChildAt(0).getWidth(),
                    (screenWidth / stepCount) * currentStepCount
            )).start();
        }
    }

    public void back() {
        if (inProgress) {
            stop();
        }
        if (!isRunning) {
            currentStepCount--;
            getValueAnimator(ValueAnimator.ofInt(
                    getChildAt(0).getWidth(),
                    (screenWidth / stepCount) * currentStepCount
            )).start();
        }
    }

    public Stepper progress(int loopSize) {
        if (!isRunning && !inProgress) {
            progressValueAnimator = getValueAnimator(ValueAnimator.ofInt(0, screenWidth));
            final int[] leftOffset = {1}; // Declare as array to make it effectively final

            progressValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int currentVal = (int) valueAnimator.getAnimatedValue();
                    LayoutParams layoutParams = (LayoutParams) getChildAt(0).getLayoutParams();
                    layoutParams.width = (layoutParams.leftMargin < screenWidth && currentVal < screenWidth) ? currentVal : 0;
                    layoutParams.leftMargin += leftOffset[0];
                    leftOffset[0] += 1;
                    getChildAt(0).setLayoutParams(layoutParams);
                }
            });

            progressAnimationListener = new Animator.AnimatorListener() {
                @Override
                public void onAnimationRepeat(Animator animation) {
                    LayoutParams layoutParams = (LayoutParams) getChildAt(0).getLayoutParams();
                    layoutParams.leftMargin = 0;
                    getChildAt(0).setLayoutParams(layoutParams);
                    leftOffset[0] = 1;
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    stop();
                }

                @Override
                public void onAnimationCancel(Animator animation) {}

                @Override
                public void onAnimationStart(Animator animation) {}
            };

            progressValueAnimator.addListener(progressAnimationListener);

            progressValueAnimator.setRepeatCount(loopSize == 0 ? Animation.INFINITE : loopSize - 1);
            progressValueAnimator.setDuration(1000);
            progressValueAnimator.start();
            inProgress = true;
        }
        return this;
    }

    public void stop() {
        if (progressValueAnimator != null && inProgress) {
            progressValueAnimator.removeAllListeners();
            progressValueAnimator.end();
            progressValueAnimator.cancel();
            getChildAt(0).setLayoutParams(new LayoutParams(
                    screenWidth - (int) (screenWidth * Math.abs(currentStepCount - stepCount)),
                    getChildAt(0).getHeight()
            ));
        }
        isRunning = false;
        inProgress = false;
    }
}