package me.zheroandre.bungeeutils.utils;

public class MessageUtils {

    public enum SuccessMessage {
        CHAT_FORMAT(" &d&lC &8[&7[SERVER]&8] &d[PLAYER] &7[MESSAGE]"),
        CHAT_ENABLE(" &d&lSTAFF &7Hai settato la &dSTAFF-CHAT &7su &dENABLE "),
        CHAT_DISABLE(" &d&lSTAFF &7Hai settato la &dSTAFF-CHAT &7su &dDISABLE "),

        REPORT_SUCCESS(" &d&lREPORT &7Hai reportato con successo &d[TARGET] "),
        REPORT_SUCCESS_ALERT(" &c&lR &8[&7[SERVER]&8] &c[PLAYER] &7ha reportato &c[TARGET] "),
        REPORT_REASON("&r \n" + "&r  &c&lREASON&r " + "&r &7[REASON]&r" + "&r        \n &r");

        public final String MESSAGE;

        SuccessMessage(String MESSAGE) {
            this.MESSAGE = MESSAGE;
        }
    }

    public enum ErrorMessage {
        NO_CONSOLE(" &c&lERROR &7Questo comando non è eseguibile da &dCONSOLE "),
        NO_PERMISSION(" &c&lERROR &7Comando sconosciuto, riprova "),
        NO_PLAYER_ONLINE(" &c&lERROR &7Questo player non risulta online "),

        SERVER_OFFLINE(" &c&lERROR &7In questo momento il server &d[SERVER] &7è offline "),
        SERVER_ALREADY(" &c&lERROR &7In questo momento sei già nel server &d[SERVER] "),

        COMMAND_EXAMPLE(" &c&lERROR &7La sintassi è &8( &d[COMMAND] &8) "),
        COMMAND_TIMER(" &c&lERROR &7Puoi eseguire questo comando ogni &d[TIME] "),
        COMMAND_YOU_SELF(" &c&lERROR &7Non puoi eseguire questo comando su te stesso "),
        COMMAND_DENIED(" &c&lERROR &7In questo momento il comando è bloccato "),

        CONTROL_ALREADY(" &c&lERROR &7Questo player è già in controllo "),
        CONTROL_IS_NOT(" &c&lERROR &7Questo player non è in controllo ");

        public final String MESSAGE;

        ErrorMessage(String MESSAGE) {
            this.MESSAGE = MESSAGE;
        }
    }

    public static String message(SuccessMessage successMessage) {
        return successMessage.MESSAGE;
    }

    public static String message(ErrorMessage errorMessage) {
        return errorMessage.MESSAGE;
    }

}
