form.addEventListener("submit", handleSubmit);

async function handleSubmit(event)
{
    event.preventDefault();

    const json =
    {
        username: username.value,
        email: email.value,
        password: password.value,
        birthday: birthday.value
    };

    const url = "/user/register";
    const headers = {"Content-Type": "application/json"};
    const requestContent = {method: "POST", headers: headers, body: JSON.stringify(json)};

    const response = await fetch(url, requestContent);

    if (response.status === 400)
    {
        alert("Bad username or password, or under 13");
        return;
    }

    localStorage.setItem("username", username.value);
    alert(`Welcome ${username.value}!`);
}