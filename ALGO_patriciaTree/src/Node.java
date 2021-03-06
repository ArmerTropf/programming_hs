

public class Node {
	int m_Key;
	Node m_Left;
	Node m_Right;
	int m_BitPos;

	public Node(int key, int bitPos) {
		this(key, bitPos, null);
	}

	public Node(int key, int bitPos, Node succ) {
		m_Key = key;
		m_BitPos = bitPos;
		boolean left = left(key, bitPos);
		m_Left = left ? this : succ;
		m_Right = left ? succ : this;
	}

	public boolean isGold() {
		return (m_Left == null && m_Right == this) || (m_Left == this && m_Right == null);
	}
	
	public boolean isNull() {
		return m_Left == null || m_Right == null;
	}
	
	public boolean hasSelfLink() {
		return (m_Left != null && m_Left == this) || (m_Right != null && m_Right == this); 
	}
	
	public boolean hasBackLink() {
		return (m_Left != null && m_Left.m_BitPos < this.m_BitPos) || (m_Right != null && m_Right.m_BitPos < this.m_BitPos); 
	}

	@Override
	public String toString() {
		return "Bit: " + m_BitPos + " Key: " + m_Key;
	}
	
	private boolean left(int c, int bitPos) {
		return (c & (1 << bitPos)) == 0;
	}

	private boolean left(char c, int bitPos) {
		return (c & (1 << bitPos)) == 0;
	}
}