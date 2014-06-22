class Pair<T, V> 
{
    private final T m_first;
    private final V m_second;

    public Pair(T first, V second)
    {
        m_first = first;
        m_second = second;
    }

    public T first() 
    {
        return m_first;
    }

    public V second() 
    {
        return m_second;
    }
}