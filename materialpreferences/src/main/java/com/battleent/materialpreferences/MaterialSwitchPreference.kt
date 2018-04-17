package com.battleent.materialpreferences

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Parcel
import android.os.Parcelable
import android.preference.Preference
import android.preference.SwitchPreference
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*

/**
 * Created by skydoves on 2017. 12. 1.
 * Copyright (c) 2017 battleent rights reserved.
 */

class MaterialSwitchPreference : SwitchPreference, CompoundButton.OnCheckedChangeListener {

    private lateinit var title: TextView
    private lateinit var summary: TextView
    private lateinit var parent: RelativeLayout
    private var switchView: Switch? = null

    private var titleColor: Int = Color.BLACK
    private var titleSize: Float = 16f

    private var summaryColor: Int = Color.BLACK
    private var summarySize: Float = 14f

    private var padding_left: Float = 16f
    private var padding_top: Float = 13f
    private var padding_right: Float = 0f
    private var padding_bottom: Float = 13f

    private var track_checked_color = Color.WHITE
    private var track_unchecked_color = Color.WHITE
    private var thumb_checked_color = Color.WHITE
    private var thumb_unchecked_color = Color.WHITE

    private var switchWidth: Int = 50
    private var background: Int = Color.WHITE

    private var mChecked = false

    constructor(context: Context): super(context) {
        onCreate()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        onCreate()
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        onCreate()
        getAttrs(attrs, defStyleAttr)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialSwitchPreference)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyleAttr: Int) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialSwitchPreference, defStyleAttr, 0)
        setTypeArray(typeArray)
    }

    private fun onCreate() {
        this.titleColor = ContextCompat.getColor(context, R.color.black_87)
        this.summaryColor = ContextCompat.getColor(context, R.color.black_three_54)
        this.background = ContextCompat.getColor(context, R.color.white_two)
        this.switchWidth = getDp(50)
        this.track_checked_color= ContextCompat.getColor(context, R.color.purple)
        this.thumb_checked_color = ContextCompat.getColor(context, R.color.purple)
        this.track_unchecked_color = ContextCompat.getColor(context, R.color.greyish_brown)
        this.thumb_unchecked_color = ContextCompat.getColor(context, R.color.white_three)
    }

    private fun setTypeArray(typeArray: TypedArray) {
        try {
            this.titleColor = typeArray.getColor(R.styleable.MaterialSwitchPreference_pref_switch_title_color, titleColor)
            this.titleSize = typeArray.getDimension(R.styleable.MaterialSwitchPreference_pref_switch_title_size, titleSize)
            this.summaryColor = typeArray.getColor(R.styleable.MaterialSwitchPreference_pref_switch_summary_color, summaryColor)
            this.summarySize = typeArray.getDimension(R.styleable.MaterialSwitchPreference_pref_switch_summary_size, summarySize)
            this.background = typeArray.getColor(R.styleable.MaterialSwitchPreference_pref_switch_background, background)
            this.switchWidth = typeArray.getDimension(R.styleable.MaterialSwitchPreference_pref_switch_width, switchWidth.toFloat()).toInt()
            this.padding_left = typeArray.getDimension(R.styleable.MaterialSwitchPreference_pref_switch_padding_left, getDp(padding_left.toInt()).toFloat())
            this.padding_top = typeArray.getDimension(R.styleable.MaterialSwitchPreference_pref_switch_padding_top, getDp(padding_top.toInt()).toFloat())
            this.padding_right = typeArray.getDimension(R.styleable.MaterialSwitchPreference_pref_switch_padding_right, getDp(padding_right.toInt()).toFloat())
            this.padding_bottom = typeArray.getDimension(R.styleable.MaterialSwitchPreference_pref_switch_padding_bottom, getDp(padding_bottom.toInt()).toFloat())
            this.track_checked_color = typeArray.getColor(R.styleable.MaterialSwitchPreference_pref_switch_checked_track_color, track_checked_color)
            this.thumb_checked_color = typeArray.getColor(R.styleable.MaterialSwitchPreference_pref_switch_checked_thumb_color, thumb_checked_color)
            this.track_unchecked_color = typeArray.getColor(R.styleable.MaterialSwitchPreference_pref_switch_unchecked_track_color, track_unchecked_color)
            this.thumb_unchecked_color = typeArray.getColor(R.styleable.MaterialSwitchPreference_pref_switch_unchecked_thumb_color, thumb_unchecked_color)
        } finally {
            typeArray.recycle()
        }
    }

    override fun onCreateView(parent: ViewGroup): View {
        parent.setBackgroundColor(ContextCompat.getColor(context, R.color.white_two))
        widgetLayoutResource = R.layout.layout_switch
        return super.onCreateView(parent)
    }

    override fun onSetInitialValue(restorePersistedValue: Boolean, defaultValue: Any?) {
        when(restorePersistedValue) {
            true -> {
                val persistingCheck = getPersistedBoolean(mChecked)
                isChecked = persistingCheck
            }
            false -> isChecked = defaultValue as Boolean
        }
    }

    override fun onBindView(view: View) {
        super.onBindView(view)
        val customSwitch = view.findViewById<View>(R.id.custom_switch_item)

        customSwitch?.let {
            (it as Checkable).isChecked = isChecked
            if (customSwitch is Switch) {
                switchView = customSwitch
                switchView?.let {
                    it.width = switchWidth
                    it.switchMinWidth = switchWidth
                    it.textOn = ""
                    it.textOff = ""
                    it.isChecked = mChecked
                    it.setOnCheckedChangeListener(this)
                }
            }
        }

        view.setPadding(0, 0, 0, 0)
        title = view.findViewById(android.R.id.title)
        title.setPadding(0, 0, 0, 0)
        title.setTextColor(ContextCompat.getColor(context, R.color.black_87))
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)

        summary = view.findViewById(android.R.id.summary)
        summary.setPadding(0, getDp(6), 0, 0)
        summary.setTextColor(ContextCompat.getColor(context, R.color.black_three_54))
        summary.setTextSize(TypedValue.COMPLEX_UNIT_SP, summarySize)

        val toggleButton = view.findViewById<LinearLayout>(android.R.id.widget_frame)
        toggleButton.setPadding(0, 0, getDp(16), 0)

        parent = summary.parent as RelativeLayout
        parent.setPadding(padding_left.toInt(), padding_top.toInt(), padding_right.toInt(), padding_bottom.toInt())

        onChangeSwitchColor()
    }

    override fun onGetDefaultValue(a: TypedArray, index: Int): Any {
        return a.getBoolean(index, false)
    }

    override fun onCheckedChanged(compoundButton: CompoundButton, isChecked: Boolean) {
        if (callChangeListener(isChecked)) {
            setChecked(isChecked)
        }
    }

    override fun setChecked(checked: Boolean) {
        if (checked != mChecked) {
            mChecked = checked
            persistBoolean(mChecked)

            switchView?.let {
                it.isChecked = mChecked
                onChangeSwitchColor()
            } ?: notifyChanged()

            notifyDependencyChange(mChecked)
        }
    }

    private fun onChangeSwitchColor() {
        switchView?.let{
            when(mChecked) {
                true -> {
                    it.trackDrawable?.setColorFilter(track_checked_color, PorterDuff.Mode.SRC_IN)
                    it.thumbDrawable?.setColorFilter(thumb_checked_color, PorterDuff.Mode.SRC_IN)
                }
                false -> {
                    it.trackDrawable?.setColorFilter(track_unchecked_color, PorterDuff.Mode.SRC_IN)
                    it.thumbDrawable?.setColorFilter(thumb_unchecked_color, PorterDuff.Mode.SRC_IN)
                }
            }
        }
    }

    override fun isChecked(): Boolean {
        return mChecked
    }

    private fun getDp(size: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (size * scale + 0.5f).toInt()
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        if (isPersistent) {
            return superState
        }

        val myState = SavedState(superState)
        myState.checked = mChecked
        return myState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state == null || state.javaClass != SavedState::class.java) {
            super.onRestoreInstanceState(state)
            return
        }
        val myState = state as SavedState?
        super.onRestoreInstanceState(myState!!.superState)

        isChecked = myState.checked
    }

    /*
     * Saving and restoring the Preference's state.
     */
    private class SavedState : Preference.BaseSavedState {
        internal var checked: Boolean = false

        constructor(superState: Parcelable) : super(superState)
        constructor(source: Parcel) : super(source) {
            checked = source.readValue(javaClass.classLoader) as Boolean
        }

        override fun writeToParcel(dest: Parcel, flags: Int) {
            super.writeToParcel(dest, flags)
            dest.writeValue(checked)
        }

        companion object {
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {

                override fun createFromParcel(`in`: Parcel): SavedState {
                    return SavedState(`in`)
                }

                override fun newArray(size: Int): Array<SavedState> {
                    return newArray(size)
                }
            }
        }
    }
}
