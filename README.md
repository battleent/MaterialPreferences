# MaterialPreferences ![license](https://img.shields.io/badge/license-MIT%20License-blue.svg)
:watermelon: An Android library that lets you implement customizing MaterialPreferences on Setting UI by [Battle Entertainment](https://www.battleent.com/).<br><br>

![preview](https://user-images.githubusercontent.com/24237865/38999773-8a566d6a-442d-11e8-869c-812f61d8db91.gif)

## Including in your project
#### build.gradle
```xml
dependencies {
  implementation "com.github.battleent:MaterialPreferences:0.3.0"
}
```

## Usage
Can be used just like using Preferences.
```xml
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <com.battleent.materialpreferences.MaterialPreferenceCategory
        android:title="Notification"
        app:pref_category_background="@color/white_three"
        app:pref_category_title_color="@color/colorPrimary"/>

    <com.battleent.materialpreferences.MaterialSwitchPreference
        android:defaultValue="true"
        android:key="Notification0"
        android:title="Marketing"
        android:summary="receive events, promotions like marketing messages."
        app:pref_switch_title_color="@android:color/holo_blue_dark"
        app:pref_switch_summary_color="@android:color/holo_blue_light"/>

    <com.battleent.materialpreferences.MaterialSwitchPreference
        android:defaultValue="true"
        android:key="Notification1"
        android:title="Snooze"
        android:summary="do not receiving notifications at night."
        app:pref_switch_title_color="@android:color/holo_green_dark"
        app:pref_switch_summary_color="@android:color/holo_green_light"
        app:pref_switch_checked_thumb_color="@android:color/holo_blue_dark"
        app:pref_switch_checked_track_color="@android:color/holo_blue_light"
        app:pref_switch_unchecked_thumb_color="@android:color/holo_green_dark"
        app:pref_switch_unchecked_track_color="@android:color/holo_green_light"/>

    <com.battleent.materialpreferences.MaterialPreference
        android:defaultValue="true"
        android:title="More"
        android:summary="show more details"/>
</PreferenceScreen>
```

### MaterialPreferenceCategory
Material PreferenceCategory is composed of just title. It is used to split sections.
```xml
<com.battleent.materialpreferences.MaterialPreferenceCategory
        android:title="Category"
        app:pref_category_background="@color/white_three"
        app:pref_category_title_color="@color/md_deep_orange_700"
        app:pref_category_title_size="18sp"
        app:pref_category_padding_bottom="6dp"
        app:pref_category_padding_left="6dp"
        app:pref_category_padding_top="6dp"/>
```

### MaterialPreference
Material Preference is composed of title and summary.
```xml
<com.battleent.materialpreferences.MaterialPreference
        android:defaultValue="true"
        android:title="More"
        android:summary="show more details"
        app:pref_title_color="@android:color/holo_green_dark"
        app:pref_summary_color="@android:color/holo_green_light"
        app:pref_title_size="18sp"
        app:pref_summary_size="13sp"
        app:pref_title_background="@color/greyish_brown"
        app:pref_summary_background="@android:color/white"/>
```

### MaterialSwitchPreference
Material Preference is composed of title, summary and switch. It saves switch's on-off status.
```xml
<com.battleent.materialpreferences.MaterialSwitchPreference
        android:defaultValue="true"
        android:key="Notification1"
        android:title="Snooze"
        android:summary="do not receiving notifications at night."
        app:pref_switch_background="@color/white_three"
        app:pref_switch_title_color="@android:color/holo_green_dark"
        app:pref_switch_summary_color="@android:color/holo_green_light"
        app:pref_switch_checked_thumb_color="@android:color/holo_blue_dark"
        app:pref_switch_checked_track_color="@android:color/holo_blue_light"
        app:pref_switch_unchecked_thumb_color="@android:color/holo_green_dark"
        app:pref_switch_unchecked_track_color="@android:color/holo_green_light"/>
```

### MaterialCheckBoxPreference
Material Preference is composed of title, summary and checkbox. It saves checkbox's isChecked status.
```xml
    <com.battleent.materialpreferences.MaterialCheckBoxPreference
        android:defaultValue="true"
        android:key="Notification4"
        android:title="Marketing"
        android:summary="receive events, promotions like marketing messages."
        app:pref_checkbox_background="@color/background800"
        app:pref_checkbox_title_color="@android:color/holo_blue_light"
        app:pref_checkbox_title_background="@color/background"
        app:pref_checkbox_summary_color="@android:color/white"/>
```

### MaterialProgressPreference
Material Preference is componsed of title, summary and progress.<br>
It can be used to showing progress during synchronize or fetching data.
```xml
<com.battleent.materialpreferences.MaterialProgressPreference
        android:title="Marketing"
        android:summary="receive events, promotions like marketing messages."
        app:pref_progress_visibility="true"/>
```
you can set visibility using below method.
```java
.visible(Boolean visibility)
```

# License
```xml
MIT License

Copyright (c) 2018 Battle Entertainment

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
