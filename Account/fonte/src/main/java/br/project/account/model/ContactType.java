package br.project.account.model;

/**
 * Project Account
 * @author Ronaldo
 */
public class ContactType {

   public enum Type {
        cel(1, "Celular", "C"),
        phone(2, "Telefone", "T"),
        mail(3, "E-mail", "E"),
        message(4, "Messages", "M"),
        others(5, "Outros", "O");

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