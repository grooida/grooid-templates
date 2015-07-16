package ${defaultPackage}

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import groovy.transform.CompileStatic
import com.arasthel.swissknife.annotations.OnUIThread

@CompileStatic
class MainActivity extends Activity {
      
    @Override
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

    @Override
    boolean onCreateOptionsMenu(Menu menu) {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    @Override
    boolean onOptionsItemSelected(MenuItem item) {
        int id = item.itemId

        if (id == R.id.action_about) {
            showAbout()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    @OnUIThread
    void showAbout() {
         new AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_launcher)
            .setTitle(R.string.app_name)
            .setView(layoutInflater.inflate(R.layout.about, null, false))
            .create()
            .show()
    }
    
}
