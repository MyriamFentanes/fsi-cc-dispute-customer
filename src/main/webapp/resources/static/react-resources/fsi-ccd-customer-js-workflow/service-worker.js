"use strict";var precacheConfig=[["/index.html","cc2cd57396d6aacf6ae8d1a3cb490a22"],["/static/css/main.css","1916225f0a451b98d442bb1024a3d639"],["/static/js/main.js","40fb92e8cae8d08a2ee0e76a3812c880"],["/static/media/fontawesome-webfont.eot","674f50d287a8c48dc19ba404d20fe713"],["/static/media/fontawesome-webfont.svg","912ec66d7572ff821749319396470bde"],["/static/media/fontawesome-webfont.ttf","b06871f281fee6b241d60582ae9369b9"],["/static/media/fontawesome-webfont.woff","fee66e712a8a08eef5805a46892932ad"],["/static/media/fontawesome-webfont.woff2","af7ae505a9eed503f8b8e6982036873e"],["/static/media/glyphicons-halflings-regular.eot","f4769f9bdb7466be65088239c12046d1"],["/static/media/glyphicons-halflings-regular.svg","89889688147bd7575d6327160d64e760"],["/static/media/glyphicons-halflings-regular.ttf","e18bbf611f2a2e43afc071aa2f4e1512"],["/static/media/glyphicons-halflings-regular.woff","fa2772327f55d8198301fdb8bcfc8158"],["/static/media/glyphicons-halflings-regular.woff2","448c34a56d699c29117adc64c43affeb"],["/static/media/header-profile-skin-1.png","85efa900c0fc12fee15a5398deba06e8"],["/static/media/header-profile-skin-2.png","8307c45ca34d4af71912b535b6c05c54"],["/static/media/header-profile-skin-3.png","bf471ec3d4085883e061ca35006e86e8"],["/static/media/shattered.png","ea2316224d45899c59bc285ba09dd920"]],cacheName="sw-precache-v3-sw-precache-webpack-plugin-"+(self.registration?self.registration.scope:""),ignoreUrlParametersMatching=[/^utm_/],addDirectoryIndex=function(e,t){var a=new URL(e);return"/"===a.pathname.slice(-1)&&(a.pathname+=t),a.toString()},cleanResponse=function(t){return t.redirected?("body"in t?Promise.resolve(t.body):t.blob()).then(function(e){return new Response(e,{headers:t.headers,status:t.status,statusText:t.statusText})}):Promise.resolve(t)},createCacheKey=function(e,t,a,n){var r=new URL(e);return n&&r.pathname.match(n)||(r.search+=(r.search?"&":"")+encodeURIComponent(t)+"="+encodeURIComponent(a)),r.toString()},isPathWhitelisted=function(e,t){if(0===e.length)return!0;var a=new URL(t).pathname;return e.some(function(e){return a.match(e)})},stripIgnoredUrlParameters=function(e,a){var t=new URL(e);return t.hash="",t.search=t.search.slice(1).split("&").map(function(e){return e.split("=")}).filter(function(t){return a.every(function(e){return!e.test(t[0])})}).map(function(e){return e.join("=")}).join("&"),t.toString()},hashParamName="_sw-precache",urlsToCacheKeys=new Map(precacheConfig.map(function(e){var t=e[0],a=e[1],n=new URL(t,self.location),r=createCacheKey(n,hashParamName,a,/\.\w{8}\./);return[n.toString(),r]}));function setOfCachedUrls(e){return e.keys().then(function(e){return e.map(function(e){return e.url})}).then(function(e){return new Set(e)})}self.addEventListener("install",function(e){e.waitUntil(caches.open(cacheName).then(function(n){return setOfCachedUrls(n).then(function(a){return Promise.all(Array.from(urlsToCacheKeys.values()).map(function(t){if(!a.has(t)){var e=new Request(t,{credentials:"same-origin"});return fetch(e).then(function(e){if(!e.ok)throw new Error("Request for "+t+" returned a response with status "+e.status);return cleanResponse(e).then(function(e){return n.put(t,e)})})}}))})}).then(function(){return self.skipWaiting()}))}),self.addEventListener("activate",function(e){var a=new Set(urlsToCacheKeys.values());e.waitUntil(caches.open(cacheName).then(function(t){return t.keys().then(function(e){return Promise.all(e.map(function(e){if(!a.has(e.url))return t.delete(e)}))})}).then(function(){return self.clients.claim()}))}),self.addEventListener("fetch",function(t){if("GET"===t.request.method){var e,a=stripIgnoredUrlParameters(t.request.url,ignoreUrlParametersMatching),n="index.html";(e=urlsToCacheKeys.has(a))||(a=addDirectoryIndex(a,n),e=urlsToCacheKeys.has(a));var r="/index.html";!e&&"navigate"===t.request.mode&&isPathWhitelisted(["^(?!\\/__).*"],t.request.url)&&(a=new URL(r,self.location).toString(),e=urlsToCacheKeys.has(a)),e&&t.respondWith(caches.open(cacheName).then(function(e){return e.match(urlsToCacheKeys.get(a)).then(function(e){if(e)return e;throw Error("The cached response that was expected is missing.")})}).catch(function(e){return console.warn('Couldn\'t serve response for "%s" from cache: %O',t.request.url,e),fetch(t.request)}))}});