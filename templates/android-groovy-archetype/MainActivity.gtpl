package ${defaultPackage}

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem


class MainActivity extends Activity {

    @Override
    void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    @Override
    boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu)
        return true
    }

    @Override
    boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId()

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
