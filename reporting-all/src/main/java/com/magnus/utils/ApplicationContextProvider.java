package com.magnus.utils;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * Provides access to application's beans
 */
public class ApplicationContextProvider implements ApplicationContextAware{

	private static ApplicationContextProvider instance;

	private ApplicationContext context;

	protected ApplicationContextProvider() {
		ApplicationContextProvider.setInstance(this);
    }

	public Object getBean(String beanName) throws BeansException {
		return this.getContext().getBean(beanName);
	}

	public <T> T getBean(String beanName, Class<T> clazz) throws BeansException {
		return clazz.cast(this.getContext().getBean(beanName, clazz));
	}

	public <T> T getBean(Class<T> clazz) throws BeansException {
		try {
			return this.getBean(this.getBeanName(clazz), clazz);
		} catch (BeansException e) {
			// do nothing
		}

		Map<String, T> beansMap = this.getBeansOfType(clazz);
		Collection<T> beans = beansMap.values();

		if (beans.size() != 1) {
			if (beans.isEmpty()) {
				throw new NoSuchBeanDefinitionException(clazz, "Unable to find the bean type "
						+ clazz.getCanonicalName());
			}
			throw new BeanCreationException("We found more than one bean definition for the type "
					+ clazz.getCanonicalName());
		}

		return beans.iterator().next();
	}

	private <T> String getBeanName(Class<T> clazz) {
		String className = clazz.getSimpleName();
		String result = uncapitalizeFirstCharacter(className);

		return result;
	}
	
	private static String uncapitalizeFirstCharacter(String string) {
		char[] stringChars = string.toCharArray();
		stringChars[0] = Character.toLowerCase(stringChars[0]);
		String result = new String(stringChars);

		return result;
    }

	public <T> Map<String, T> getBeansOfType(Class<T> clazz) throws BeansException {
		return this.getContext().getBeansOfType(clazz);
	}


	public ApplicationContext getContext() {
		return this.context;
	}

	public static ApplicationContextProvider getInstance() {
		if (instance == null) {
			throw new RuntimeException("Instance is null. Check that this bean is loaded first");
		}
	    return instance;
    }
	protected static void setInstance(ApplicationContextProvider instance) {
		ApplicationContextProvider.instance = instance;
    }

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		this.context = arg0;
	}
}
