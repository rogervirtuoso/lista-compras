var app = angular.module("ListaCompras", [])
    .value('urlBase', 'http://localhost:8080/lista-compras/rest/')
    .controller("ComprasController", function ($http, urlBase) {
        var self = this;

        self.compras = [];
        self.compra = undefined;
        self.total = undefined;

        self.novo = function () {
            self.compra = {};
            self.comprasItemList = [];
        };

        self.adicionarItem = function () {
            self.comprasItemList.push({
                descricaoProduto: self.item.descricaoProduto,
                quantidade: self.item.quantidade,
                valor: self.item.valor
            });
            self.cleanItem();
        };

        self.removerItem = function (compra) {
            var index = self.comprasItemList.indexOf(compra);
            if (index > -1) {
                self.comprasItemList.splice(index, 1);
            }
        };

        self.salvar = function () {
            var metodo = 'POST';
            if (self.compra.compras.id) {
                metodo = 'PUT';
            }

            self.compra.comprasItemList = self.comprasItemList;

            $http({
                method: metodo,
                url: urlBase + 'compras/',
                data: self.compra
            }).then(function successCallback(response) {
                self.compras = response.data;
                self.atualizarTabela();
            }, function errorCallback(response) {
                self.ocorreuErro(response.data);
            });
        };

        self.alterar = function (compra) {
            self.compra = compra;
            self.compra.comprasItemList = [];

            $http({
                method: 'GET',
                url: urlBase + 'compras/' + self.compra.compras.id + '/'
            }).then(function successCallback(response) {
                self.comprasItemList = response.data.comprasItemList;
            }, function errorCallback(response) {
                self.ocorreuErro(response.data);
            });
        };

        self.deletar = function (compra) {
            self.compra = compra;

            $http({
                method: 'DELETE',
                url: urlBase + 'compras/' + self.compra.compras.id + '/'
            }).then(function successCallback(response) {
                self.atualizarTabela();
            }, function errorCallback(response) {
                self.ocorreuErro(response.data);
            });
        };

        self.ocorreuErro = function (msg) {
            if (msg != undefined) {
                alert(msg)
            } else {
                alert("Ocorreu um erro inesperado!");
            }
        };

        self.atualizarTabela = function () {
            $http({
                method: 'GET',
                url: urlBase + 'compras/'
            }).then(function successCallback(response) {
                self.compras = response.data;
                self.compra = undefined;
                self.valorTotal();
            }, function errorCallback(response) {
                self.ocorreuErro(response.data);
            });
        };

        self.valorTotal = function () {
            $http({
                method: 'GET',
                url: urlBase + 'total/'
            }).then(function successCallback(response) {
                self.total = response.data;
            }, function errorCallback(response) {
                self.ocorreuErro(response.data);
            });
        };

        self.isEmpty = function isEmpty(obj) {
            for (var prop in obj) {
                if (obj.hasOwnProperty(prop) && obj.prop != "" && obj.prop != undefined)
                    return false;
            }
            return true;
        }

        self.clean = function clean() {
            self.compra = undefined;
            self.comprasItemList = undefined;
            self.item = undefined;
            self.atualizarTabela();
        }

        self.cleanItem = function cleanItem() {
            self.item.descricaoProduto = " ";
            self.item.quantidade = 1;
            self.item.valor = 0.00;
        }

        self.activate = function () {
            self.atualizarTabela();
        };
        self.activate();
    });

app.directive('ngConfirmClick', [
    function () {
        return {
            link: function (scope, element, attr) {
                var msg = attr.ngConfirmClick;
                var clickAction = attr.confirmedClick;
                element.bind('click', function (event) {
                    if (window.confirm(msg)) {
                        scope.$eval(clickAction)
                    }
                });
            }
        };
    }])