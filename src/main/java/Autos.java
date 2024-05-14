import java.util.ArrayList;
import java.util.List;



public class Autos {
	
	private ArrayList<Auto> auto = new ArrayList<Auto>();
	
	public Autos() {
	}

	public Autos(ArrayList<Auto> listaAutos) {
		this.auto = listaAutos;
	}

	public void añadirAuto(Auto auto){
		this.auto.add(auto);
	}

	public ArrayList<Auto> getAuto() {
		return auto;
	}

	public void setAuto(ArrayList<Auto> auto) {
		this.auto = auto;
	}


	
}
