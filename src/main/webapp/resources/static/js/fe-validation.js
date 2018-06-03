var feValidation = (function($){

    var init = function() {
        $("body").on("submit", "form.fe-validation", function(e) {

            var allowSubmission = true;
        
            $.each($(e.target).find("input.fe-validation"), function(n, i) {
                if (!validateField($(i))) {
                    allowSubmission = false;
                }
            });

            if (!allowSubmission) {
                displayErrorToast();
            }

            return allowSubmission;
        });

        $("body").on("blur", "input.fe-validation", function(e) {
            validateField($(e.target)); 
        });
    };

    var validateField = function($field) {
        if ($field.data("fe-required") === true && $field.val().trim().length === 0) {
            addError($field, "this field is mandatory.");
            return false;
        }

        var validator = $field.data("fe-type");
        try {
            if (!validators[validator]($field)) {
                return false;
            }

            addSuccess($field);
            return true;
        } catch(err) {
            console.error('validator ' + validator + ' not found');
            addSuccess($field);
            return true;
        }
    };

    var addError = function($field, error) {
        $field.parent().addClass("has-error").removeClass("has-success");
        $field.next("span.help-block").remove();
        $field.after("<span class=\"help-block m-b-none\">" + error + "</span>");
    };

    var addSuccess = function($field) {
        $field.parent().addClass("has-success").removeClass("has-error");
        $field.next("span.help-block").remove();
    };

    var displayErrorToast = function(message) {
        toastr["error"](message || "The request could not be sent, please check the fields with errors.")
    };

    var validators = {
        string: function($field) {
            return this.length($field);
        },
        email: function($field) {
            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            if (!re.test($field.val().toLowerCase())) {
                addError($field, "the email address is not valid.");
                return false;
            }

            return this.length($field);
        },
        uppercase: function($field) {
            var re = /^[A-Z]*$/;
            if (!re.test($field.val())) {
                addError($field, "only uppercase letters are allowed.");
                return false;
            }

            return this.length($field);
        },
        number: function($field) {
            var re = /^[0-9]*$/;
            if (!re.test($field.val())) {
                addError($field, "only numbers are allowed.");
                return false;
            }

            return this.length($field);
        },
        regex: function($field) {
            var re = new RegExp($field.data("fe-regex"));
            if (!re.test($field.val())) {
                addError($field, $field.data("fe-error"));
                return false;
            }

            return this.length($field);
        },
        length: function($field) {
            var minLength = $field.data("fe-min-length") || 1;
            var maxLength = $field.data("fe-max-length") || 0;
            var currentLength = $field.val().trim().length;

            if (currentLength < minLength || currentLength > maxLength) {
                addError($field, "the value has to have a length in between " + minLength + " and " + maxLength + " characters");
                return false;
            }

            return true;
        }
    };

    return {
        init: init,
        validateField: validateField,
        displayErrorToast: displayErrorToast
    };
    
})(jQuery);

$(document).ready(feValidation.init);
