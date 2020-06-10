package helpers;

public class Helpers {

	public void espera(int seconds) {
		try {
			Thread.sleep(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
