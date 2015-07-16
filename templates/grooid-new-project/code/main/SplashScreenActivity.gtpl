package ${defaultPackage}

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.arasthel.swissknife.annotations.OnBackground

import groovy.transform.CompileStatic

@CompileStatic
class SplashScreenActivity extends Activity {

    static final long DELAY = 3000

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        startSomethingElse()
    }

    @OnBackground
    void startSomethingElse() {
        Thread.sleep(DELAY)
        startActivity(new Intent(this, MainActivity))
        finish()
    }

}
