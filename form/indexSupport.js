var cardTemplate = '';



function myOnloadFunc() {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange =
      function() {
    if (this.readyState == 4 && this.status == 200) {
      console.log(JSON.parse(xhr.responseText));
      var jsonResponse = JSON.parse(xhr.responseText);

      // <img src="${element.img}" class="card-img-top">
      jsonResponse.forEach(
          element => {

              cardTemplate +=
              `<div class="col col-md-4 d-flex align-items-stretch">
                    <div class="card m-2">
                        <img src=${element.img} class="card-img-top">
                        <div class="card-body">
                            <div class="m-2 h-50">
                                <div class="h-50">
                                    <h5 class="card-title">${element.title}</h5>
                                </div>
                                <div class="h-50">
                                    <p class="card-text" id="card-text">
                                        ${element.description}
                                    </p>
                                </div>
                            </div>
                            <div class="d-flex justify-content-between align-items-end h-50">
                                <a href="#" class="btn btn-primary m-1 ">
                                    Add
                                </a>
                                <a href="#" class="btn btn-primary m-1 float-right">
                                    Remove
                                </a>
                            </div>
                        </div>
                    </div>
                </div>`});
    }
    document.getElementById('productsContainer').innerHTML = cardTemplate;
  }


      xhr.open('GET', 'serverScripts/getProducts.php', true);
  xhr.send();
}
