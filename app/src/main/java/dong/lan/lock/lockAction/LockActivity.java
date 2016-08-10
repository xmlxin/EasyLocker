
package dong.lan.lock.lockAction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.WindowManager;

import dong.lan.lock.BaseActivity;
import dong.lan.lock.R;
import dong.lan.lock.lockConfig.SettingPresenter;

/*
 * 锁频页面
 */

public class LockActivity extends BaseActivity implements SettingPresenter.ConfigChange {

    private LockViewManager viewManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
        super.onCreate(savedInstanceState);

        setupView(R.layout.activity_lock);
        viewManager = LockViewManager.getInstance(LockActivity.this);
        viewManager.setLockView(LayoutInflater.from(LockActivity.this).inflate(R.layout.view_lock,null));
        viewManager.updateActivity(LockActivity.this);
        viewManager.Lock();
        SettingPresenter.addConfigChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        viewManager.updateActivity(LockActivity.this);
        super.onDestroy();

    }

    //设置页面设置密码模型的回调，保证下次锁频时使用的时最新的密码模型
    @Override
    public void onPatterChange(String patter) {
        viewManager.updatePatter(patter);
    }

    //锁频页面屏蔽返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        int key = event.getKeyCode();
        return key == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }
}
