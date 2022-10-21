import java.io.FileReader;
import java.util.ArrayList;
import java.io.BufferedReader;

public class ClassProductList extends ArrayList<Product> {
	public ClassProductList() {}
	public void initProductList() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("database\\ProductInfo.txt"));
			String line = reader.readLine();
			while(line != null) {
				String[] data = line.split(":");
				Product product = new Product(data[1], (data[0].equals("Produce")?1:0));
				this.add(product);
				line = reader.readLine();
			}
		} catch(Exception e) {
			System.out.println("Error! While reading file ProductInfo.");
			System.out.println(e.getStackTrace());
		}
	}
}