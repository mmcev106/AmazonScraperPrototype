(
    function(){

// I'm not sure why the following line didn't work
//        return document.querySelector('#newPitchPriceWrapper_feature_div').textContent.trim()

// Here's an example where I parsed the ISBN like an idiot, but I thought the "xpath" solution was cool so I left if in case you end up needing something similar.
//        return document.evaluate(\"//th[contains(text(), 'ISBN-10')]\", document, null, XPathResult.ANY_TYPE, null).iterateNext().nextElementSibling.textContent.trim();


        return 12345
    }
)();