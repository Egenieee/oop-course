public class EnStringArrayList {
    private String[] data = new String[300];
    private int index_end = 300;
    private int idx = 0;

    public void add(String val) {
        if (idx < index_end) { //추가하려는 스트링의 순서가 미리 만들어 놓은 배열의 크기보다 작을 경우
            data[idx] = val;
            idx++;
        } else if (idx == index_end) {//추가하려는 스트링의 순서가 미리 만들어 놓은 배열의 크기와 같을 경우
            index_end *= 2;
            String[] newarr = new String[index_end]; //2배로 크게 만든다.
            for (int i = 0; i < index_end; i++) {
                if (i == idx) {//값이 있는 리스트의 끝을 만났을 때, 끝에 값을 넣어준다.
                    newarr[i] = val;
                    idx++;
                    break;
                }
                newarr[i] = data[i];
            }
            data = newarr;//새 배열의 주소를 data에 할당한다.
        }

    }

    public boolean insert(int index, String val) {
        if (index < 0 || index > index_end) { //유효한 값인지 체크
            return false;
        }

        String idx_tmp = data[index]; //값 insert하기 전 원래 index에 있던 값 복사해서 temp에 넣어놓기
        data[index] = val;
        idx++;

        if (index == index_end) {
            add(val);
            return true;
        }

        String tmp2;

        for (int i = index + 1; i < idx; i++) {
            tmp2 = data[i];
            data[i] = idx_tmp;
            idx_tmp = tmp2;
        }

        return true;
    }

    public boolean remove(int index) {
        if (index < 0 || index >= index_end || index > idx) {
            return false;
        }
        if (index == index_end - 1) { //ArrayList 맨 끝의 원소를 지울 경우
            data[index] = null;
            idx--;
            return true;
        } else if (index <= index_end - 1) { //지울 인덱스가 맨 끝의 원소가 아닐 경우
            data[index] = data[index + 1]; //지울 인덱스 자리에 인덱스 다음 원소 값을 대입

            for (int i = index + 1; i < idx; i++) {
                if (data[i + 1] == null) {
                    data[i] = null;
                    break;
                }
                data[i] = data[i + 1];
            }
            idx--;
            return true;
        }

        return false;

    }

    public String get(int index) {
        if (index >= 0 && index < index_end) {
            return data[index];
        }
        return null;
    }

    public boolean set(int index, String val) {
        if (index >= 0 && index < index_end) {
            data[index] = val;
            return true;
        }
        return false;
    }

    public boolean contains(String val) {
        return (indexOf(val) >= 0);
    }

    public int indexOf(String val) {
        for (int i = 0; i < index_end; i++) {
            if (data[i].equals(val)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return idx;
    }

//    public int arr_size() {
//        return index_end;
//    }

    public void println() {
        for (String a : data) {
            if (a == null) break;
            System.out.print(" " + a);
        }
        System.out.println();
    }
}
