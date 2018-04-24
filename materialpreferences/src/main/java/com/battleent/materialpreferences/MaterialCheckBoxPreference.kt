
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 battleent
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.battleent.materialpreferences

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.preference.Preference
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.*

class MaterialCheckBoxPreference : Preference, CompoundButton.OnCheckedChangeListener {

    private lateinit var title: TextView
    private lateinit var summary: TextView
    private lateinit var parent: RelativeLayout
    private var checkBox: CheckBox? = null

    private var titleColor: Int = Color.BLACK
    private var titleSize: Float = 16f

    private var summaryColor: Int = Color.BLACK
    private var summarySize: Float = 14f

    private var padding_left: Float = 16f
    private var padding_top: Float = 10f
    private var padding_right: Float = 0f
    private var padding_bottom: Float = 10f

    private var background: Int = Color.WHITE

    private var mChecked = false

    /**
     * Construct a new MaterialCheckBoxPreference with default style options.
     *
     * @param context The Context that will style this preference
     */
    constructor(context: Context): super(context) {
        onCreate()
    }

    /**
     * Construct a new MaterialCheckBoxPreference with the given style options.
     *
     * @param context The Context that will style this preference
     * @param attrs Style attributes that differ from the default
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        onCreate()
        getAttrs(attrs)
    }

    /**
     * Construct a new MaterialCheckBoxPreference with the given style options.
     *
     * @param context The Context that will style this preference
     * @param attrs Style attributes that differ from the default
     * @param defStyleAttr An attribute in the current theme that contains a
     *        reference to a style resource that supplies default values for
     *        the view. Can be 0 to not look for defaults.
     */
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        onCreate()
        getAttrs(attrs, defStyleAttr)
    }

    /**
     * Construct a new MaterialCheckBoxPreference with the given style options.
     *
     * @param attrs Style attributes that differ from the default
     */
    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialCheckBoxPreference)
        setTypeArray(typedArray)
    }

    /**
     * Construct a new MaterialCheckBoxPreference with the given style options.
     *
     * @param attrs Style attributes that differ from the default
     * @param defStyleAttr An attribute in the current theme that contains a
     *        reference to a style resource that supplies default values for
     *        the view. Can be 0 to not look for defaults.
     */
    private fun getAttrs(attrs: AttributeSet, defStyleAttr: Int) {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.MaterialCheckBoxPreference, defStyleAttr, 0)
        setTypeArray(typeArray)
    }

    /**
     * Set default Styleable values.
     */
    private fun onCreate() {
        this.titleColor = ContextCompat.getColor(context, R.color.black_87)
        this.summaryColor = ContextCompat.getColor(context, R.color.black_three_54)
        this.background = ContextCompat.getColor(context, R.color.white_two)
    }

    /**
     * A {@link Preference} that provides a two-state toggleable option.
     *
     * @attr ref android.R.styleable#MaterialCheckBoxPreference_pref_checkbox_title_color
     * @attr ref android.R.styleable#MaterialCheckBoxPreference_pref_checkbox_title_size
     * @attr ref android.R.styleable#MaterialCheckBoxPreference_pref_checkbox_summary_color
     * @attr ref android.R.styleable#MaterialCheckBoxPreference_pref_checkbox_summary_size
     * @attr ref android.R.styleable#MaterialCheckBoxPreference_pref_checkbox_background
     * @attr ref android.R.styleable#MaterialCheckBoxPreference_pref_checkbox_padding_left
     * @attr ref android.R.styleable#MaterialCheckBoxPreference_pref_checkbox_padding_top
     * @attr ref android.R.styleable#MaterialCheckBoxPreference_pref_checkbox_padding_right
     * @attr ref android.R.styleable#MaterialCheckBoxPreference_pref_checkbox_padding_bottom
     */
    private fun setTypeArray(typeArray: TypedArray) {
        try {
            this.titleColor = typeArray.getColor(R.styleable.MaterialCheckBoxPreference_pref_checkbox_title_color, titleColor)
            this.titleSize = typeArray.getDimension(R.styleable.MaterialCheckBoxPreference_pref_checkbox_title_size, titleSize)
            this.summaryColor = typeArray.getColor(R.styleable.MaterialCheckBoxPreference_pref_checkbox_summary_color, summaryColor)
            this.summarySize = typeArray.getDimension(R.styleable.MaterialCheckBoxPreference_pref_checkbox_summary_size, summarySize)
            this.background = typeArray.getColor(R.styleable.MaterialCheckBoxPreference_pref_checkbox_background, background)
            this.padding_left = typeArray.getDimension(R.styleable.MaterialCheckBoxPreference_pref_checkbox_padding_left, getDp(padding_left.toInt()).toFloat())
            this.padding_top = typeArray.getDimension(R.styleable.MaterialCheckBoxPreference_pref_checkbox_padding_top, getDp(padding_top.toInt()).toFloat())
            this.padding_right = typeArray.getDimension(R.styleable.MaterialCheckBoxPreference_pref_checkbox_padding_right, getDp(padding_right.toInt()).toFloat())
            this.padding_bottom = typeArray.getDimension(R.styleable.MaterialCheckBoxPreference_pref_checkbox_padding_bottom, getDp(padding_bottom.toInt()).toFloat())
        } finally {
            typeArray.recycle()
        }
    }

    /**
     * Initialize widget Layout Resource with customized switch.
     *
     */
    override fun onCreateView(parent: ViewGroup): View {
        parent.setBackgroundColor(ContextCompat.getColor(context, R.color.white_two))
        widgetLayoutResource = R.layout.layout_checkbox
        return super.onCreateView(parent)
    }

    /**
     * Set initial value of checkbox is checked.
     *
     * <p>
     * This preference will store a boolean into the SharedPreferences.
     */
    override fun onSetInitialValue(restorePersistedValue: Boolean, defaultValue: Any?) {
        when(restorePersistedValue) {
            true -> {
                val persistingCheck = getPersistedBoolean(mChecked)
                setChecked(persistingCheck)
            }
            false -> setChecked(defaultValue as Boolean)
        }
    }

    /**
     * Set initial value of checkbox is checked.
     *
     * <p>
     *     it initialize styleable data
     */
    override fun onBindView(view: View) {
        super.onBindView(view)
        val customCheckBox = view.findViewById<View>(R.id.custom_checkbox_item)
        view.setBackgroundColor(background)

        customCheckBox?.let {
            (it as Checkable).isChecked = isChecked()
            if (customCheckBox is CheckBox) {
                checkBox = customCheckBox
                checkBox?.let {
                    it.isChecked = mChecked
                    it.setOnCheckedChangeListener(this)
                }
            }
        }

        view.setPadding(0, 0, 0, 0)
        title = view.findViewById(android.R.id.title)
        title.setPadding(0, 0, 0, 0)
        title.setTextColor(titleColor)
        title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize)

        summary = view.findViewById(android.R.id.summary)
        summary.setPadding(0, getDp(6), 0, getDp(7))
        summary.setTextColor(summaryColor)
        summary.setTextSize(TypedValue.COMPLEX_UNIT_SP, summarySize)

        val toggleButton = view.findViewById<LinearLayout>(android.R.id.widget_frame)
        toggleButton.setPadding(0, 0, getDp(16), 0)

        parent = summary.parent as RelativeLayout
        parent.setPadding(padding_left.toInt(), padding_top.toInt(), padding_right.toInt(), padding_bottom.toInt())
    }

    /**
     * Get default value
     */
    override fun onGetDefaultValue(a: TypedArray, index: Int): Any {
        return a.getBoolean(index, false)
    }

    override fun onCheckedChanged(compoundButton: CompoundButton, isChecked: Boolean) {
        // Listener didn't like it, change it back.
        // CompoundButton will make sure we don't recurse.
        if (callChangeListener(isChecked)) {
            setChecked(isChecked)
        }
    }

    /**
     * GET returns isChecked switch.
     */
    fun isChecked(): Boolean {
        return mChecked
    }

    /**
     * SET sets isChecked switch.
     */
    fun setChecked(checked: Boolean) {
        if (checked != mChecked) {
            mChecked = checked
            persistBoolean(mChecked)

            checkBox?.let {
                it.isChecked = mChecked
            } ?: notifyChanged()

            notifyDependencyChange(mChecked)
        }
    }

    /**
     * Get DP value from PX.
     */
    private fun getDp(size: Int): Int {
        val scale = context.resources.displayMetrics.density
        return (size * scale + 0.5f).toInt()
    }

    /**
     * Get onSaveInstanceState.
     */
    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        if (isPersistent) {
            return superState
        }

        val myState = SavedState(superState)
        myState.checked = mChecked
        return myState
    }

    /**
     * Get onRestoreInstanceState.
     */
    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state == null || state.javaClass != SavedState::class.java) {
            super.onRestoreInstanceState(state)
            return
        }
        val myState = state as SavedState?
        super.onRestoreInstanceState(myState!!.superState)

        setChecked(myState.checked)
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
