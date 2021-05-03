package model;

import java.util.Objects;

public class Curr {
    private String name;
    private Long count = 0L;

    public Curr(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curr curr = (Curr) o;
        return name.equals(curr.name);
    }
}
