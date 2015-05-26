package com.morsca.weezevent.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.morsca.weezevent.exception.WeezeventException;
import com.morsca.weezevent.mapper.WeezeventMapperFactory;

public class WeezeventRestTemplateClient implements WeezeventHttpClient {

	private final RestTemplate restTemplate;

	public WeezeventRestTemplateClient() {
		this.restTemplate = new RestTemplate();
		
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new MappingJackson2HttpMessageConverter(WeezeventMapperFactory.getWeezeventMapper()));
		restTemplate.setMessageConverters(messageConverters);
	}
	
	public WeezeventRestTemplateClient(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Override
	public <T> T get(String url, Class<T> clazz) throws WeezeventException {
		return restTemplate.getForEntity(url, clazz).getBody();
	}

	@Override
	public <T> T get(String url, Map<String, String> params, Class<T> clazz) throws WeezeventException {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>(); 
		for(Entry<String, String> entry : params.entrySet()) {
			body.add(entry.getKey(), entry.getValue());
		}
		return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<Object>(body), clazz).getBody();
	}

	@Override
	public <T> T post(String url, Map<String, String> params, Class<T> clazz) throws WeezeventException {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>(); 
		for(Entry<String, String> entry : params.entrySet()) {
			body.add(entry.getKey(), entry.getValue());
		}
		return restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<Object>(body), clazz).getBody();
	}

	@Override
	public void close() throws WeezeventException {
		//do nothing
	}

}
