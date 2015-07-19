package ${defaultPackage}

import android.app.Activity
import android.widget.TextView
import com.andrewreitz.spock.android.AndroidSpecification
import com.andrewreitz.spock.android.UseActivity

class MainActivitySpec extends AndroidSpecification {

    @UseActivity(MainActivity)
    Activity mainActivity

    void 'check greetings message from main activity'() {
        given: 'a text view at the main activity'
        TextView textView = mainActivity.findViewById(R.id.groovy_label)

        expect: 'the content to contain Groovy'
        textView.text =~ 'Groovy'
    }

}