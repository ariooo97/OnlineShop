<br/>
<br/>
<footer class="main-footer" ng-controller="contentCtrl">
    <div class="container">
        <div class="row">
            <div class="col">
                <div>
                    <div class="footer-circle-item">
                        <i class="fa fa-map-pin"></i>
                    </div>
                    <div class="footer-circle-item-title" ng-bind-html="getContent('address')"></div>
                </div>

                <div>
                    <div class="footer-circle-item">
                        <i class="fa fa-phone"></i>
                    </div>
                    <a href="tel:{{getContent('tel') |  removeHTMLTags}}" class="footer-circle-item-title" ng-bind-html="getContent('tel')"></a>
                </div>
                <div>
                    <div class="footer-circle-item">
                        <i class="fa fa-envelope"></i>
                    </div>
                    <a href="mailto:{{getContent('email') |  removeHTMLTags}}" class="footer-circle-item-title" ng-bind-html="getContent('email')"></a>
                </div>
            </div>
            <div class="col">
                <h4>About The Company</h4>
                <br/>
                <p class="footer-description" ng-bind-html="getContent('footer-about')"></p>
                <br/>
                <div>
                    <a href="{{getContent('instagram-link') | removeHTMLTags}}" class="footer-social">
                        <i class="fa fa-instagram"></i>
                    </a>
                    <a href="{{getContent('telegram-link') | removeHTMLTags}}" class="footer-social">
                        <i class="fab fa-telegram"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>
</footer>