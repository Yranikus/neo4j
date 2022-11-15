
const getURL = "http://localhost:8090/getlistofstudents"



const tableBody = document.querySelector('.bodyContainer')





const getTableContent = (getURL, table) => {
    const response = fetch(getURL);
    let counter = 0;
        response.then(response => {
            if (response.status == 200) {
                return response.json();
            } else {
                alert(`Ошибочка с запросом вышла, а код вот такой ${response.status}`);
                throw new Error(response.status);
            }
        })
        .then(res => {
            res.forEach(item => {
                counter++;
                table.insertAdjacentHTML("beforeend",
                    `<tr class="studentRow">
                        <td class="row">${item.id}</td>
                        <td>${item.name}</td>
                        <td class="minWidth">
                            <form method="post" action="/activateordeactivate?name=${item.name}&status=${(item.status !== true)}">
                            <input type="submit" class="status" value="${item.status === true ? 'деактивировать' : 'активироваать'}" id="${item.id}">
                            </form>
                        </td>
                    </tr>`);
            })
        }).then(() => {
        buttons = document.querySelectorAll('.BBB');
    });
};

getTableContent(getURL, tableBody);


