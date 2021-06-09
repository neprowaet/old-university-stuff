public class Main {
 public static String toString(Node<Integer, Character> node) {
        return node.process(Object::toString, (c, a, b) -> "(" + a + " " + c.toString() + " " + b + ")");
    }

    public static Integer value(Node<Integer, Character> node) {
        return node.process(a -> a, (c, a, b) ->  {
            switch (c){
                case '+':
                    return a+b;
                case '*':
                    return a*b;
                case '-':
                    return a-b;
                default: return 0;
            }
        });
    }

    public static Node<Integer, Character> invert(Node<Integer, Character> node) {
        return node.process(a -> (Node<Integer, Character>) new Leaf<Integer, Character>(-a), BiNode::new);
    }

    public static void main(String[] args) {
        Node<Integer, Character> root = new BiNode<>('+', new Leaf<>(4), new Leaf<>(-5));
        Node<Integer, Character> second = new BiNode<>('*', root, new Leaf<>(6));
        Node<Integer, Character> third = new BiNode<>('-', second, new Leaf<>(7));

        System.out.println("toString(third): " + toString(third));
        System.out.println("value(third): " + value(third));
        System.out.println("toString(invert(third): " + toString(invert(third)));
        System.out.println("value(invert(third)): " + value(invert(third)));
    }
}
