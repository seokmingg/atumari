document.addEventListener('DOMContentLoaded', function () {
    const fileInput = document.getElementById('files');
    const fileList = document.getElementById('selected-file-list');
    const maxFileCount = 3;
    let selectedFiles = [];

    if (!fileInput || !fileList) {
        return;
    }

    const fileKey = function (file) {
        return [file.name, file.size, file.lastModified].join(':');
    };

    const syncFileInput = function () {
        const transfer = new DataTransfer();
        selectedFiles.forEach(function (file) {
            transfer.items.add(file);
        });
        fileInput.files = transfer.files;
    };

    const renderFileList = function () {
        fileList.replaceChildren();

        selectedFiles.forEach(function (file, index) {
            const item = document.createElement('li');
            const name = document.createElement('span');
            const removeButton = document.createElement('button');

            name.className = 'selected-file-name';
            name.textContent = file.name;

            removeButton.type = 'button';
            removeButton.className = 'selected-file-remove';
            removeButton.textContent = '×';
            removeButton.setAttribute('aria-label', file.name + ' 取り消し');
            removeButton.addEventListener('click', function () {
                selectedFiles.splice(index, 1);
                syncFileInput();
                renderFileList();
            });

            item.append(name, removeButton);
            fileList.appendChild(item);
        });
    };

    fileInput.addEventListener('change', function () {
        const existingKeys = new Set(selectedFiles.map(fileKey));
        const newFiles = Array.from(fileInput.files).filter(function (file) {
            return !existingKeys.has(fileKey(file));
        });

        const availableCount = maxFileCount - selectedFiles.length;
        selectedFiles = selectedFiles.concat(newFiles.slice(0, availableCount));

        if (newFiles.length > availableCount) {
            window.alert('添付ファイルは最大3個まで選択できます。');
        }

        syncFileInput();
        renderFileList();
    });
});
