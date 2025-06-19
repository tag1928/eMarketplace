form.addEventListener("submit", handleSubmit);

async function handleSubmit(event)
{
    event.preventDefault();

    const json =
    {
        username: username.value,
        password: password.value,
    };

    const url = "/user/login";
    const headers = {"Content-Type": "application/json"};
    const requestBody = {method: "POST", headers: headers, body: JSON.stringify(json)};

    const response = await fetch(url, requestBody);

    if (response.status === 401)
    {
        alert("Incorrect username or password");
        return;
    }

    localStorage.setItem("username", username.value);
    alert(`Welcome ${username.value}!`);
}