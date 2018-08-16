package br.project.account.model;


/**
 * Project Account
 * @author Ronaldo
 */
public class AddressType {

    public enum Type {
        comercial(0, "Commercial", "C"),
        delivery(1, "Entrega", "E"),
        residential(2, "Residecial", "R"),
        others(3, "Outros", "O");

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

}