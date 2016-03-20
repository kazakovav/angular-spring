package org.akazakov.common.translation.controller;

import org.akazakov.common.translation.UrlPath;
import org.akazakov.common.translation.impl.SerializableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Properties;

@RestController
@RequestMapping(value = UrlPath.MESSAGE_BUNDLE_PATH)
public class MessageBundleController {

	private SerializableResourceBundleMessageSource bundleMessageSource;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public Properties list(@RequestParam("lang") String lang) {
		return bundleMessageSource.getAllProperties(new Locale(lang));
	}

	public SerializableResourceBundleMessageSource getBundleMessageSource() {
		return bundleMessageSource;
	}

	public void setBundleMessageSource(SerializableResourceBundleMessageSource bundleMessageSource) {
		this.bundleMessageSource = bundleMessageSource;
	}
}
