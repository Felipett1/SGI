//LOGIN
function showPassword() {

    var key_attr = $('#key').attr('type');

    if (key_attr != 'text') {

        $('.checkbox').addClass('show');
        $('#key').attr('type', 'text');

    } else {

        $('.checkbox').removeClass('show');
        $('#key').attr('type', 'password');

    }

}
//MENU
(function ($) {
    $(document).ready(function () {
        $('#cssmenu > ul > li > a').click(function () {
            $('#cssmenu > ul > li').removeClass('active');
            $(this).closest('li').addClass('active');
            var checkElement = $(this).next();
            if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                $(this).closest('li').removeClass('active');
                checkElement.slideUp('normal');
            }
            if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                $('#cssmenu ul ul:visible').slideUp('normal');
                checkElement.slideDown('normal');
            }
            /*if ($(this).closest('li').find('ul').children().length == 0) {
             return true;
             } else {
             return false;
             }*/
        });

        $('#cssmenu > ul > li > ul > li > a').click(function () {
            $('#cssmenu li li').removeClass('active');
            $(this).closest('li').addClass('active');
            var checkElement = $(this).next();
            if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
                $(this).closest('li').removeClass('active');
                checkElement.slideUp('normal');
            }
            if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
                $('#cssmenu ul ul ul:visible').slideUp('normal');
                checkElement.slideDown('normal');
            }
            /*if ($(this).closest('li').find('ul').children().length == 0) {
             return true;
             } else {
             return false;
             }*/
        });
    });
})(jQuery);
