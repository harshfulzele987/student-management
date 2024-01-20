package student.managment.entity;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ClassRange {
	Class_1(1) , Class_2(2) ,Class_3(3), Class_4(4), Class_5(5), Class_6(6), Class_7(7), Class_8(8), Class_9(9), Class_10(10);
	private final int range;

    ClassRange(int range) {
        this.range= range;
    }
    
    @JsonValue
    public int getValue() {
        return range;
    }
}
