package stan.grobex.sample

import android.app.Activity
import android.os.Bundle
import stan.grobex.view.frameLayout
import stan.grobex.view.textView
import stan.grobex.view.textViewConfig

class MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = frameLayout(context = this) {
            textView(textViewConfig = textViewConfig(text = "test"))
        }
        setContentView(contentView)
    }
}
