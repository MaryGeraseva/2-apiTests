package petStore.models.storeModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Inventory{

	@JsonProperty("additionalProp1")
	private int additionalProp1;

	@JsonProperty("additionalProp3")
	private int additionalProp3;

	@JsonProperty("additionalProp2")
	private int additionalProp2;

	public Inventory() {
	}

	public void setAdditionalProp1(int additionalProp1){
		this.additionalProp1 = additionalProp1;
	}

	public int getAdditionalProp1(){
		return additionalProp1;
	}

	public void setAdditionalProp3(int additionalProp3){
		this.additionalProp3 = additionalProp3;
	}

	public int getAdditionalProp3(){
		return additionalProp3;
	}

	public void setAdditionalProp2(int additionalProp2){
		this.additionalProp2 = additionalProp2;
	}

	public int getAdditionalProp2(){
		return additionalProp2;
	}

	@Override
 	public String toString(){
		return 
			"Inventory{" + 
			"additionalProp1 = '" + additionalProp1 + '\'' +
			",additionalProp3 = '" + additionalProp3 + '\'' +
			",additionalProp2 = '" + additionalProp2 + '\'' +
			"}";
	}
}