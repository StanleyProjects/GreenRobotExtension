package stan.grobex.sample

import android.app.Activity
import android.os.Bundle
import stan.grobex.view.Orientation
import stan.grobex.view.linearLayout
import stan.grobex.view.textView

class MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(linearLayout(context = this, orientation = Orientation.VERTICAL) {
            textView(text = "test")
            textView(text = "test2", isAllCaps = true)
        })
    }
}
