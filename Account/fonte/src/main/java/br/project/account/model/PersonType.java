package br.project.account.model;

/**
 * Project Account
 * @author Ronaldo Torre
 */
public class PersonType {

    public enum Type {
        physical(1, "Física", "F"),
        juridical(2, "Jurídica", "J");

        private Integer code;
        private String name;
        private String initial;

        private Type(Integer code, String name, String initial) {
            this.code = code;
            this.name = name;
            this.initial = initial;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getInitial() {
            return initial;
        }
    }

    public enum Gender {
        female(1, "Feminino", "F"),
        male(2, "Masculino", "M");

        private Integer code;
        private String name;
        private String initial;

        private Gender(Integer code, String name, String initial) {
            this.code = code;
            this.name = name;
            this.initial = initial;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public String getInitial() {
            return initial;
        }
    }

    public enum StateCivil {
        married(1, "Casado(a)"),
        single(2, "Solteiro(a)"),
        flirt(3, "Namorando"),
        separate(4, "Separado(a)"),
        divorced(5, "Divorciado(a)"),
        Widower(6, "Viúvo(a)");

        private Integer code;
        private String name;

        private StateCivil(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

}
