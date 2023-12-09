package tester;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dependent.ATMImpl;

public class TestSpring {

	public static void main(String[] args) {
		// Boot SC: using XML based metadata instructions in runtime classpath  
		try(ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("SpringBean_config.xml") ) 
		{
		System.out.println("SC Started");	
		// BeanFactory i/f method public <T> T getBean(String beanId, Class<T> class)...
		
		ATMImpl ref1=ctx.getBean("my_atm", ATMImpl.class);//1st demand
		ref1.withdraw(500);
		
		ATMImpl ref2=ctx.getBean("my_atm", ATMImpl.class);//2nd demand
		
		System.out.println(ref1==ref2);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
