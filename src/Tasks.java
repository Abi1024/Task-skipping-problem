import java.util.ArrayList;
import java.util.Objects;

public class Tasks
{
	public ArrayList<Integer> tasks;

	public Tasks(ArrayList tasks){
		this.tasks = tasks;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Tasks))
		{
			return false;
		}
		Tasks tasks1 = (Tasks) o;
		return tasks.equals(tasks1.tasks);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(tasks);
	}
}
