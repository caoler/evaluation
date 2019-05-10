package cn.caoler.evaluation.enums;

import lombok.Getter;

/**
 * Created by caole on 2019/3/17 15:11
 */
@Getter
public enum ResultEnum {

    SUCCESS(0,"�ɹ�"),

    PARAM_ERROR(1,"��������ȷ"),

    LOGIN_FAIL(10,"��¼ʧ��,��¼��Ϣ����ȷ"),

    USER_NOT_EXIST(11,"�û�������"),

    PASSWORD_ERROR(12,"�������"),

    ROLE_REJECT(13,"�㲻ӵ�д˽�ɫ"),

    ROLE_NOT_EXIST(14,"��ɫ������"),

    UPDATE_ERROR(15,"����ʧ��"),

    CREATE_ERROR(16,"����ʧ��"),

    COURSE_NOT_EXIST(17,"�γ̲�����"),


    DEPARTMENT_NOT_EXIST(18,"���Ų�����"),

    CLAZZ_NOT_EXIST(19,"�༶������"),

    ACTIONROLE_NOT_EXIST(20,"��ɫȨ�޲���"),

    INDEX_NOT_EXIST(21,"ָ�겻����"),


    OPTION_NOT_EXIST(22,"ѡ�����"),


    SEMESTER_NOT_EXIST(23,"ѧ�ڲ�����"),


    GRADE_NOT_EXIST(24,"�ɼ�������"),

    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
