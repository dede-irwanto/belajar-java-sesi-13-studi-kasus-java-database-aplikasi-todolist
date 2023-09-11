package repository;

import entity.Todolist;

public class TodolistRepositoryImpl implements TodolistRepository {
    public Todolist[] data = new Todolist[10];
    @Override
    public Todolist[] getAll() {
        return data;
    }

    private boolean isFull() {
        var isFull = true;
        for (var i = 0; i < data.length; i++) {
            // model masih ada yang kosong
            if (data[i] == null) {
                isFull = false;
                break;
            }
        }
        return  isFull;
    }

    private void resizeIfFull() {
        // jika penuh, resize ukuran model 2x lipat
        if (isFull()) {
            var temp = data;
            data = new Todolist[data.length * 2];

            for (var i = 0; i < temp.length; i++) {
                data[i] = temp[i];
            }
        }
    }

    @Override
    public void add(Todolist todolist) {
        resizeIfFull();
        // tambah ke posisi yang data array nya null
        for (var i = 0; i < data.length; i++) {
            if (data[i] == null) {
                data[i] = todolist;
                break;
            }
        }
    }

    @Override
    public boolean remove(Integer number) {
        if ((number - 1) >= data.length) {
            return false;
        } else if (data[number - 1] == null) {
            return false;
        } else {
            for (var i = (number - 1); i < data.length; i++) {
                if (i == (data.length - 1)) {
                    data[i] = null;
                } else {
                    data[i] = data[i + 1];
                }
            }
            return true;
        }
    }
}
