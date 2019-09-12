package com.sandeep.fancyalertdialog

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


class FancyAlertDialog private constructor(builder: Builder) {
    private val title: String?
    private val message: String?
    private val positiveBtnText: String?
    private val negativeBtnText: String?
    private val activity: Activity
    private val icon: Int
    private val visibility: Icon?
    private val animation: Animation?
    private val pListener: FancyAlertDialogListener?
    private val nListener: FancyAlertDialogListener?
    private val pBtnColor: Int
    private val nBtnColor: Int
    private val bgColor: Int
    private val cancel: Boolean

    private val noPositiveBtn: Boolean



    class Builder(val activity: Activity) {
        var title: String? = null
        var message: String? = null
        var positiveBtnText: String? = null
        var negativeBtnText: String? = null
        var icon = 0
        var visibility: Icon? = null
        var animation: Animation? = null
        var pListener: FancyAlertDialogListener? = null
        var nListener: FancyAlertDialogListener? = null
        var pBtnColor = 0
        var nBtnColor = 0
        var bgColor = 0
        var cancel = false

        var noPositiveBtn = false
        fun setTitle(title: String?): Builder {
            this.title = title
            return this
        }

        fun setBackgroundColor(bgColor: Int): Builder {
            this.bgColor = bgColor
            return this
        }

        fun setMessage(message: String?): Builder {
            this.message = message
            return this
        }

        fun setPositiveBtnText(positiveBtnText: String?): Builder {
            this.positiveBtnText = positiveBtnText
            return this
        }

        fun setNoPositiveBtn(noPositiveBtn: Boolean): Builder {
            this.noPositiveBtn = noPositiveBtn
            return this
        }


        fun setPositiveBtnBackground(pBtnColor: Int): Builder {
            this.pBtnColor = pBtnColor
            return this
        }

        fun setNegativeBtnText(negativeBtnText: String?): Builder {
            this.negativeBtnText = negativeBtnText
            return this
        }

        fun setNegativeBtnBackground(nBtnColor: Int): Builder {
            this.nBtnColor = nBtnColor
            return this
        }

        //setIcon
        fun setIcon(
            icon: Int
        ): Builder {
            this.icon = icon
            this.visibility = Icon.Visible
            return this
        }

        fun setAnimation(animation: Animation?): Builder {
            this.animation = animation
            return this
        }

        //set Positive listener
        fun OnPositiveClicked(pListener: FancyAlertDialogListener?): Builder {
            this.pListener = pListener
            return this
        }

        //set Negative listener
        fun OnNegativeClicked(nListener: FancyAlertDialogListener?): Builder {
            this.nListener = nListener
            return this
        }

        fun isCancellable(cancel: Boolean): Builder {
            this.cancel = cancel
            return this
        }

        fun build(): FancyAlertDialog {
            val message1: TextView
            val title1: TextView
            val iconImg: ImageView
            val nBtn: Button
            val pBtn: Button
            val view: View
            val dialog: Dialog = if (animation == Animation.POP) Dialog(
                activity,
                R.style.PopTheme
            ) else if (animation == Animation.SIDE) Dialog(
                activity,
                R.style.SideTheme
            ) else if (animation == Animation.SLIDE) Dialog(
                activity,
                R.style.SlideTheme
            ) else Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.window
                .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(cancel)
            dialog.setContentView(R.layout.fancydialoglayout)

            //getting resources

            view =
                dialog.findViewById(R.id.background) as View
            title1 =
                dialog.findViewById<View>(R.id.title) as TextView
            message1 =
                dialog.findViewById<View>(R.id.message) as TextView
            iconImg =
                dialog.findViewById<View>(R.id.icon) as ImageView
            nBtn =
                dialog.findViewById<View>(R.id.negativeBtn) as Button
            pBtn =
                dialog.findViewById<View>(R.id.positiveBtn) as Button
            title1.text = title
            message1.text = message

            pBtn.isEnabled = false
            //  pBtn.isEnabled = !noPositiveBtn.equals(true)

//                if (noPositiveBtn){
//                    pBtn.visibility = View.GONE
//                }


            if (positiveBtnText != null) pBtn.text = positiveBtnText
            if (pBtnColor != 0) {
                val bgShape =
                    pBtn.background as GradientDrawable
                bgShape.setColor(pBtnColor)
            }


            if (nBtnColor != 0) {
                val bgShape =
                    nBtn.background as GradientDrawable
                bgShape.setColor(nBtnColor)
            }
            if (negativeBtnText != null) nBtn.text = negativeBtnText
            iconImg.setImageResource(icon)
            if (visibility == Icon.Visible) iconImg.visibility =
                View.VISIBLE else iconImg.visibility =
                View.GONE
            if (bgColor != 0) view.setBackgroundColor(bgColor)
            if (pListener != null) {
                pBtn.setOnClickListener {
                    pListener!!.OnClick()
                    dialog.dismiss()
                }
            } else {
                pBtn.setOnClickListener { dialog.dismiss() }
            }
            if (nListener != null) {
                nBtn.visibility = View.VISIBLE
                nBtn.setOnClickListener {
                    nListener!!.OnClick()
                    dialog.dismiss()
                }
            }
            dialog.show()
            return FancyAlertDialog(this)
        }

    }

    init {
        title = builder.title
        message = builder.message
        activity = builder.activity
        icon = builder.icon
        animation = builder.animation
        visibility = builder.visibility
        pListener = builder.pListener
        nListener = builder.nListener
        positiveBtnText = builder.positiveBtnText
        negativeBtnText = builder.negativeBtnText
        pBtnColor = builder.pBtnColor
        nBtnColor = builder.nBtnColor
        bgColor = builder.bgColor
        cancel = builder.cancel

        noPositiveBtn = builder.noPositiveBtn
    }

    enum class Icon{
        Visible, Gone
    }
    enum class Animation{
        POP, SLIDE, SIDE
    }
}
