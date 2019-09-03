package killSingle;

import java.util.ArrayList;

public class Kill {

	ArrayList<Person> persons;

	int numOfPerson;

	public Kill(int numOfPerson) {
		System.out.println("Begin person number  " + numOfPerson);
		this.numOfPerson = numOfPerson;
		this.persons = new ArrayList<Person>();
		for (int i = 0; i < this.numOfPerson; i++) {
			Person person = new Person(i + 1);
			this.persons.add(person);
		}
	}

	public void killPerson() {
		while (persons.size() != 1) {
			for (int i = 0; i < persons.size(); i++) {
				if (this.persons.get(i).isSingle) {
					this.persons.remove(i);
				}
			}
			for (int i = 0; i < persons.size(); i++) {
				Person checkPerson = persons.get(i);
				checkPerson.setPlaceNum(i + 1);
				checkPerson.checkIsSingle();
			}
		}
		System.out.println("not dead person begin number : "
				+ this.persons.get(0).getBeginNum());

	}
}
