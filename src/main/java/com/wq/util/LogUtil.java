package com.wq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogUtil {

    private static final Logger console_logger = LoggerFactory.getLogger("console_logger");
    private static final Logger info_logger = LoggerFactory.getLogger("info_logger");
    private static final Logger error_logger = LoggerFactory.getLogger("error_logger");
    private static final Logger error_mail_logger = LoggerFactory.getLogger("error_mail_logger");

    public static void console(String arg) {
        console_logger.info(arg);
    }

    public static void console(String format, Object... args) {
        console_logger.info(format, args);
    }

    public static void info(String arg) {
        info_logger.info(arg);
    }

    public static void info(String format, Object... args) {
        info_logger.info(format, args);
    }

    public static void error(String error) {
        if (error == null) {
            return;
        }
        error_logger.error(error);
    }

    public static void error(String format, Object... args) {
        if (format == null) {
            return;
        }
        error_logger.error(format, args);
    }

    public static void error(Exception e) {
        error_logger.error("", e);
    }

    public static void error(String error, Exception e) {
        error_logger.error(error, e);
    }

    public static void errorMail(String error) {
        if (error == null) {
            return;
        }
        error_mail_logger.error(error);
    }

    public static void errorMail(String format, Object... args) {
        if (format == null) {
            return;
        }
        error_mail_logger.error(format, args);
    }

    public static void errorMail(Exception e) {
        error_mail_logger.error("", e);
    }

    public static void errorMail(String error, Exception e) {
        error_mail_logger.error(error, e);
    }
}