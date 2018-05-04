package hw7;

/**
 * YOU ARE NOT ALLOWED TO ALTER THIS CLASS.
 */
public class SimpleList {

    //Initial size of the internal array.
    private static final int INITIAL_CAPACITY = 10;

    //Internal array of Entry objects.
    private Entry[] entries;

    //Size of the List
    private int size;

    /**
     * Constructor creates an empty {@code SimpleList} with default initial capacity.
     *
     */
    public SimpleList() {
        this.entries = new Entry[INITIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * This method adds a new {@code Entry} to the end of the list.  The list will also be resized when necessary.
     *
     * @param e The entry to add to the end of the list.
     */
    public void add(Entry e) {
        //Check to see if we need to resize the list
        if (this.size == this.entries.length) {
            this.resize();
        }

        this.entries[this.size] = e;
        this.size++;
    }

    /**
     * This function finds the {@code Entry} in the list whose word matches the given {@code String}.  The function
     * returns the index of where the Entry can be found, or -1 if the {@code Entry} was not found.
     *
     * @param word  The word whose {@code Entry} you want to find.
     *
     * @return  Returns the index of where the {@code Entry} was found, -1 otherwise.
     */
    public int find(String word) {

        for (int i = 0 ; i < this.size ; i++) {
            Entry current = this.entries[i];

            if (word.equals(current.getWord())) {
                return i;
            }
        }

        return -1;
    }

    /**
     * This method returns the {@code Entry} at the given index.
     *
     * @param index The index of the {@code Entry} to return.  {@code index} must be a positive value between 0 to
     *              size()-1 inclusive.
     * @return  The {@code Entry} at the given index.
     */
    public Entry getEntry(int index) {
        return this.entries[index];
    }

    /**
     * This method returns the number of entries in the list.
     *
     * @return  The number of entries in the list.
     */
    public int size() {
        return this.size;
    }

    /**
     * This method will create a new list double the size of the previous, and copy all values from the old list
     * to the new list.
     */
    private void resize() {
        Entry[] newList = new Entry[this.entries.length * 2];

        for (int i = 0 ; i < this.size ; i++) {
            newList[i] = this.entries[i];
        }

        this.entries = newList;
    }


    public String toString() {
        String result = "";

        String formatter = "%-20s%-1d";

        for (int i = 0 ; i < this.size ; i++) {
            Entry e = this.entries[i];
            result += String.format(formatter, e.getWord(), e.getCount()) + "\n";
        }

        return result;
    }
}
