using System;

namespace sharp_scratch
{
	public class InsertionSort
	{
		private int[] sortedList; // backing store
		public int[] SortedList 
		{ 
			get
			{
				return sortedList;
			}
			set
			{
				sortedList = value;
			}
		}
		public static void Main(string[] args)
		{
			int[] sortThis = {-14, 55, 332, 21, 0, -12};
			InsertionSort sorter = new InsertionSort(sortThis);
			Console.WriteLine(string.Join(",", sorter.SortedList));
		}


		 public InsertionSort(int[] list) 
		 {
			SortedList = list;
		 	Sort(list);
		 }

		 private void Sort(int[] unsorted)
		 {
			for(int i = 0; i < sortedList.Length; i++) 
		 	{
		 		int j = i;
		 		while(j > 0 && unsorted[j-1] > unsorted[j]) 
		 		{
		 			Swap(j, j-1);
		 			j--;
		 		}
		 	}
		 }

		 private void Swap(int index1, int index2) 
		 {
		 	int temp = sortedList[index1];
		 	sortedList[index1] = sortedList[index2];
		 	sortedList[index2] = temp;
		 }
	}
}
