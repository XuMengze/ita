package com.ita.alg.tree;


public class VEB {
    public Node root;

    public VEB(int length) {
        this.root = new Node(length);
    }

    private static class Node {
        private int u;
        private Integer min;
        private Integer max;
        private Node[] nodeArray;
        private Node summary;

        public Node(int u) {
            this.u = u;
            if (u != 2) {
                double pow = Math.log(u) / Math.log(2);
                int high = (int) Math.pow(2, (int) (Math.ceil(pow / 2)));
                int low = (int) Math.pow(2, (int) (Math.floor(pow / 2)));
                this.summary = new Node(high);
                this.nodeArray = new Node[low];
                for (int i = 0; i < low; i++)
                    this.nodeArray[i] = new Node(low);
            }

        }

        public boolean contain(int x) {
            if (min == x || max == x)
                return true;
            else if (u == 2)
                return false;
            else
                return nodeArray[x / nodeArray.length].contain(x % x / nodeArray.length);

        }

        public Integer successor(int x) {
            if (u == 2)
                if (x == 0 && max == 1)
                    return 1;
                else
                    return null;
            else if (min != null && x < min)
                return min;
            else {
                int high = x / nodeArray.length;
                Integer max_low = nodeArray[high].max;
                Integer offset = 0;
                if (max_low != null && x % nodeArray.length < max_low) {
                    offset = nodeArray[high].successor(x % nodeArray.length);
                    return high * nodeArray.length + offset;
                } else {
                    Integer successorCluster = summary.successor(high);
                    if (successorCluster == null)
                        return null;
                    else {
                        offset = nodeArray[successorCluster].min;
                        return successorCluster * nodeArray.length + offset;
                    }
                }
            }
        }

        public Integer predecessor(int x) {
            if (u == 2)
                if (x == 1 && min == 0)
                    return 0;
                else
                    return null;
            else if (max != null && x > max)
                return max;
            else {
                int high = x / nodeArray.length;
                int low = x % nodeArray.length;
                Integer min_low = nodeArray[high].min;
                Integer offset = 0;
                if (min_low != null && low > min_low) {
                    offset = nodeArray[high].predecessor(x);
                    return high * nodeArray.length + offset;
                } else {
                    Integer predecessorCluster = summary.predecessor(high);
                    if (predecessorCluster != null)
                        return predecessorCluster * nodeArray.length + nodeArray[predecessorCluster].predecessor(x);
                    else if (min != null && x > min)
                        return min;
                    else
                        return null;
                }
            }
        }

        private void emptyInsert(int x) {
            min = max = x;
        }

        public void insert(int x) {
            if (min == null) {
                emptyInsert(x);
            } else {
                if (x < min) {
                    int temp = x;
                    x = min;
                    min = temp;
                }
                if (u > 2) {
                    int high = x / nodeArray.length;
                    int low = x % nodeArray.length;
                    if (nodeArray[high].min == null) {
                        nodeArray[high].emptyInsert(low);
                        summary.insert(high);
                    } else
                        nodeArray[high].insert(low);
                }
                if (x > max)
                    max = x;
            }
        }

        /**
         * del方法前提是x是树中一个元素
         */
        public void del(int x) {
            if (min == max)
                min = max = null;
            else if (u == 2) {
                if (x == 0)
                    min = 1;
                else
                    min = 0;
                min = max;
            } else {
                /**
                 *此处重点理解，先判断如果x==min，那么将当前node下的最小簇的最小节点用来顶替min，
                 * 并且将x置为最小簇的最小节点，
                 * 删除
                 * 如果删掉之后，x所在的簇为null，
                 *  那么删除summary中的对应节点，用现存最大节点顶替max
                 * 如果x所在簇不为null，但是x==max
                 *  那么用x所在簇的最大节点顶替max
                 */
                if (x == min) {
                    int firstCluster = summary.min;
                    x = firstCluster * nodeArray.length + nodeArray[firstCluster].min;
                    min = x;
                }
                int high = x / nodeArray.length;
                int low = x % nodeArray.length;
                nodeArray[high].del(low);
                if (nodeArray[high].min == null) {
                    summary.del(high);
                    if (x == max) {
                        Integer summary_max = summary.max;
                        if (summary_max == null)
                            max = min;
                        else
                            max = summary_max * nodeArray.length + nodeArray[summary_max].max;
                    }
                } else if (x == max) {
                    max = high * nodeArray.length + nodeArray[high].max;
                }
            }
        }
    }

    public Integer minNum() {
        return this.root.min;
    }

    public Integer maxNum() {
        return this.root.max;
    }

    public boolean contain(int x) {
        return root.contain(x);
    }

    public Integer successor(int x) {
        return root.successor(x);
    }

    public Integer predecessor(int x) {
        return root.predecessor(x);
    }

    public void insert(int x) {
        root.insert(x);
    }

    public void del(int x) {
        root.del(x);
    }

    public static void main(String[] args) {
        Node node = new Node(32);
        System.out.print("OK");
    }
}
