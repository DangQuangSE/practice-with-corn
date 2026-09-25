export type UploadProgress = { loaded: number; total: number; percent: number };
export type UploadResult = { id: string; displayName: string; contentType: string; size: number };

export function uploadWithProgress(file: File, onProgress: (progress: UploadProgress) => void): Promise<UploadResult> {
  const form = new FormData();
  form.append("file", file);
  const baseUrl = process.env.NEXT_PUBLIC_API_BASE_URL ?? "http://localhost:8080";

  return new Promise((resolve, reject) => {
    const request = new XMLHttpRequest();
    request.open("POST", `${baseUrl}/api/files`);
    request.upload.onprogress = (event) => {
      if (event.lengthComputable) {
        onProgress({ loaded: event.loaded, total: event.total, percent: Math.round(event.loaded * 100 / event.total) });
      }
    };
    request.onerror = () => reject(new Error("Network error while uploading"));
    request.onabort = () => reject(new Error("Upload cancelled"));
    request.onload = () => {
      if (request.status < 200 || request.status >= 300) {
        reject(new Error(`Upload rejected (${request.status})`));
        return;
      }
      try {
        resolve(JSON.parse(request.responseText) as UploadResult);
      } catch {
        reject(new Error("Upload response was not valid JSON"));
      }
    };
    request.send(form);
  });
}
