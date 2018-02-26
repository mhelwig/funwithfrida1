package net.codemetrix.funwithfrida;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This is a demo app to test overriding various methods and variables with Frida (https://www.frida.re).
 */
public class MainActivity extends AppCompatActivity {

    static String static_welcome = "Hello, World (static variable)";

    String instance_welcome = "Hello, World (instance variable)";

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.loadText();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MainActivity.this.loadText();
            }
        });

    }

    protected void loadText() {
        TextView tv = (TextView) findViewById(R.id.sample_text);

        //Here we add text returned from and contained in various variables and methods of MainActivity
        String text = MainActivity.static_welcome + "\n";
        text += this.instance_welcome + "\n";
        text += generatePrivateGreeting("Hello, World (private instance method with argument)") + "\n";
        text += this.overloaded("Hello, World") + "\n";
        text += this.overloaded("Hello, World",1) + "\n";
        text += generateStaticPrivateGreeting() + "\n";

        //Call the external method
        text += stringFromJNI() + "\n";

        //Here we work with an external class
        text += Util.generateStaticGreeting() + "\n";
        Util util = new Util();
        text += util.generateGreeting();


        tv.setText(text);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application. You should be able to override that with Frida as well
     */
    public native String stringFromJNI();

    private static String generateStaticPrivateGreeting() {
        return "Hello, World (private static method)";
    }

    private String generatePrivateGreeting (String message) {
        return message;
    }

    private String overloaded(String message) {
        return "Hello, World (private overloaded method: String)";
    }

    private String overloaded(String message, int option) {
        return "Hello, World (private overloaded method: String, int)";
    }
}
