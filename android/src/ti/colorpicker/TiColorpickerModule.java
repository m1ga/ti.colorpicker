/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2018 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package ti.colorpicker;

import android.content.DialogInterface;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.ColorPickerView;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

import org.appcelerator.kroll.KrollFunction;
import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.KrollDict;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.titanium.util.TiConvert;

@Kroll.module(name="TiColorpicker", id="ti.colorpicker")
public class TiColorpickerModule extends KrollModule
{
	// Methods

	@Kroll.method
	public void showColorPicker(KrollDict args)
	{
		String dialogTitle = args.optString("title", "");
		String selectButtonTitle = args.optString("selectButtonTitle", "Select");
		String cancelButtonTitle = args.optString("cancelButtonTitle", "Cancel");
		boolean alphaSlider = args.optBoolean("alphaSlider", false);
		boolean brightnessSlider = args.optBoolean("brightnessSlider", true);
		KrollFunction onSelect = (KrollFunction)args.get("onSelect");

		ColorPickerDialog.Builder builder = new ColorPickerDialog.Builder(TiApplication.getAppCurrentActivity())
				.setPreferenceName("TiColorPickerDialog")
				.setTitle(dialogTitle)
				.setPositiveButton(selectButtonTitle, (ColorEnvelopeListener) (envelope, fromUser) -> {
					KrollDict event = new KrollDict();
					event.put("color", envelope.getHexCode());

					onSelect.callAsync(krollObject, event);
				})
				.setNegativeButton(cancelButtonTitle, (dialogInterface, i) -> dialogInterface.dismiss())
				.attachAlphaSlideBar(alphaSlider) // the default value is true.
				.attachBrightnessSlideBar(brightnessSlider)  // the default value is true.
				.setBottomSpace(12); // set a bottom space between the last slide bar and buttons.

		if (args.containsKeyAndNotNull("selectionColor")) {
			int selectionColor = TiConvert.toColor(args.get("selectionColor"), TiApplication.getAppRootOrCurrentActivity());
			ColorPickerView colorPickerView = builder.getColorPickerView();
			colorPickerView.setInitialColor(selectionColor);
		}
		builder.show();
	}
}