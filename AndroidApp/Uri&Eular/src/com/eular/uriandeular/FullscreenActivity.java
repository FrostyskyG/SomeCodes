package com.eular.uriandeular;

import com.eular.uriandeular.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
	private Toast toast;
	
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });

        
        findViewById(R.id.dummy_button).setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent m) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),Info.class));
				return false;
			}});
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        delayedHide(100);
        
        //start toast
        startChat();
    }


    private void startChat() {
    	duihua("������","����");
    	duihua("�����գ�","һ������");
    	photoToast("��ô������", R.drawable.head1,60,-120);
    	photoToast("���ո�ȥ����", R.drawable.head2,-60,160);
    	
    	duihua("��ԭ���й�Ů����?","ʮ��������ãã����˼������������");
    	duihua("����?��ô����?","ɽ���꣬��ˮΪ�ߣ�������������ѩ��");
    	duihua("ร������֡�����Щ������ô������?","���泾���̻�ɫ�����ֲԲ�ʮָ�ڡ�");
    	duihua("���������ס���ô�㿴���ҵĵ�һ�о���ʲô?","����һҹ��������ǧ�������滨����");
    	duihua("(������)����ô��?","���������Ǵ�����������д�Ǿ���");
    	duihua("��ƨ��--����������?","������������־����Ц�Ƴ����ɷ�");
    	duihua("�㡭���԰���Ŀ�����?","ֻ�ڴ�ɽ�У����֪����");
    	duihua("����ϲ��������?","����ʮ���������ү��!");
    	duihua("��ţ�����˰�?����ô��Ż�����ô������?","С��δ������ģ������Ķ����֪��");
    	duihua("(Ц)���磬����˵���磬�Ҵ�Ӧ�޸��㣬�������������?","һƬ���������!");
    	duihua("�㱣֤����Ա�����˶���?","�����Ĳ�����Ĺž�ˮ��");
    	duihua("��������һ�أ���������������ȥ�������飬���ܵ�����?","��ȥ���꣬Ӧ�������������衣");
    	duihua("��������","����ƾ�������޽�ɽ����ʱ���׼�ʱ��!");
    	duihua("���ǡ���","���򴦣������ƣ���Ϊʯ������ͷ!");
    	positionToast("���˺��ˣ������㡭����",60,-110);
    	photoToast("�š���", R.drawable.xia,-60,160);
    	
    	positionToast("��һ���",0,0);
    	duihua("�����ô�ã��㻹������ԭ�ȵ�Ů����?","�����׺���Ϊˮ����ȴ��ɽ�����ơ�");
    	duihua("��Ϊʲô���껹���ҽ��?","���ﲻ֪���ǿͣ�һ��̰����");
    	duihua("̫�����˰ɡ����Ǻô��Ƿ��ޡ�","���ޱ���ͬ���񣬴�����ͷ���Էɡ�");
    	duihua("��������λ���������ô��?","��������������ξ����ĺ�!");
    	duihua("����ô����?�㲻��˵���ҵĵ�һӡ�󡭡�","��Ů�绨��������Ω�����ɡ�");
    	duihua("������ô˵�İɣ��ѵ����㾹Ȼ����","������������䣬�񳯷ŵ�˼���ġ�");
    	duihua("һֱ��������д�Ÿ������Ҷ������ţ�û�뵽�������!","ֽ�ϵ����վ�ǳ����֪����Ҫ���С�");
    	duihua("��ԭ�ȵ����붼���Ķ�ȥ��?","�ҰѸ�����������ͳ���");
    	duihua("(��������)�㣬�㲻�Ǵ�ӦһƬ���ĵ���?","���̼������֮�ѳɻҡ�");
    	duihua("��Ͳ��������Ц����������?","���ɱ���֦ͷ�����������䱱���С�");
    	duihua("��Ҫ��ͬ�������?","��������Ϊ�ֵܣ��αط��������ס�");
    	duihua("�ã�������","��������");
    	positionToast("End",0,0);
	}


	private void duihua(String w1,String w2) {
		positionToast(w1,60,-110);
        positionToast(w2,-60,140);
	}


	Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
    
    //--------------------------
    //toast method
    private void photoToast(String words, int Rid,int x,int y) {
    	toast = Toast.makeText(getApplicationContext(), words, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, x, y);
		
		LinearLayout toastView = (LinearLayout) toast.getView();
		ImageView imageCodeProject = new ImageView(getApplicationContext());
		imageCodeProject.setImageResource(Rid);
		toastView.addView(imageCodeProject, 0);
		
		toast.show();
	}

	private void positionToast(String words,int x,int y) {
		toast = Toast.makeText(getApplicationContext(), words, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, x, y);
		toast.show();
	}
	//--------------------------
}
