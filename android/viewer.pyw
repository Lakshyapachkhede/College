import tkinter as tk
from tkinter import ttk, messagebox
from pathlib import Path


# ---------------------------------------------------------
# Configuration
# ---------------------------------------------------------

IGNORED_DIRS = {
    ".git",
    ".gradle",
    ".idea",
    "build",
    "captures",
    "externalNativeBuild",
    ".cxx"
}

IMPORTANT_EXTENSIONS = {
    ".kt",
    ".java",
    ".xml"
}


# ---------------------------------------------------------
# Main Application
# ---------------------------------------------------------

class AndroidBrowser:

    def __init__(self, root):
        self.root = root
        self.root.title("Android Project Browser")
        self.root.geometry("1100x700")
        self.root.minsize(800, 500)

        self.current_path = None
        self.project_root = None
        self.history = []

        self.setup_style()
        self.create_widgets()

    # -----------------------------------------------------
    # Styling
    # -----------------------------------------------------

    def setup_style(self):

        style = ttk.Style()

        try:
            style.theme_use("clam")
        except:
            pass

        style.configure(
            "Treeview",
            background="white",
            foreground="#222222",
            fieldbackground="white",
            rowheight=28,
            font=("Segoe UI", 10)
        )

        style.configure(
            "Treeview.Heading",
            background="#eeeeee",
            foreground="#222222",
            font=("Segoe UI", 10, "bold")
        )

        style.map(
            "Treeview",
            background=[("selected", "#dbeafe")],
            foreground=[("selected", "#111111")]
        )

    # -----------------------------------------------------
    # GUI
    # -----------------------------------------------------

    def create_widgets(self):

        # ---------------- TOP TITLE ----------------

        title = tk.Label(
            self.root,
            text="Android Project Browser",
            font=("Segoe UI", 20, "bold"),
            bg="#f5f5f5",
            fg="#222222",
            pady=12
        )

        title.pack(fill="x")

        # ---------------- PATH BAR ----------------

        path_frame = tk.Frame(
            self.root,
            bg="#f5f5f5"
        )

        path_frame.pack(
            fill="x",
            padx=15,
            pady=10
        )

        self.back_button = tk.Button(
            path_frame,
            text="← Back",
            command=self.go_back,
            bg="#e5e5e5",
            fg="#222222",
            activebackground="#d5d5d5",
            activeforeground="#222222",
            relief="flat",
            padx=15,
            pady=7,
            font=("Segoe UI", 10)
        )

        self.back_button.pack(
            side="left",
            padx=(0, 8)
        )

        self.path_entry = tk.Entry(
            path_frame,
            bg="white",
            fg="#222222",
            insertbackground="#222222",
            relief="flat",
            font=("Consolas", 11)
        )

        self.path_entry.pack(
            side="left",
            fill="x",
            expand=True,
            ipady=8
        )

        self.path_entry.bind(
            "<Return>",
            lambda event: self.go_path()
        )

        go_button = tk.Button(
            path_frame,
            text="GO",
            command=self.go_path,
            bg="#20a05a",
            fg="white",
            activebackground="#178347",
            activeforeground="white",
            relief="flat",
            padx=25,
            pady=7,
            font=("Segoe UI", 10, "bold")
        )

        go_button.pack(
            side="left",
            padx=(8, 0)
        )

        # ---------------- MAIN AREA ----------------

        main_frame = tk.Frame(
            self.root,
            bg="#f5f5f5"
        )

        main_frame.pack(
            fill="both",
            expand=True,
            padx=15,
            pady=(0, 15)
        )

        # Left side
        left_frame = tk.Frame(
            main_frame,
            bg="white"
        )

        left_frame.pack(
            side="left",
            fill="both",
            expand=False
        )

        left_frame.config(width=330)

        # Right side
        right_frame = tk.Frame(
            main_frame,
            bg="white"
        )

        right_frame.pack(
            side="left",
            fill="both",
            expand=True,
            padx=(10, 0)
        )

        # ---------------- LEFT TITLE ----------------

        self.left_title = tk.Label(
            left_frame,
            text="Projects",
            bg="white",
            fg="#222222",
            anchor="w",
            font=("Segoe UI", 11, "bold"),
            padx=10,
            pady=8
        )

        self.left_title.pack(fill="x")

        # ---------------- PROJECT LIST ----------------

        list_frame = tk.Frame(
            left_frame,
            bg="white"
        )

        list_frame.pack(
            fill="both",
            expand=True
        )

        self.project_list = tk.Listbox(
            list_frame,
            bg="white",
            fg="#222222",
            selectbackground="#dbeafe",
            selectforeground="#111111",
            relief="flat",
            borderwidth=0,
            font=("Segoe UI", 11),
            activestyle="none"
        )

        scrollbar = tk.Scrollbar(
            list_frame,
            command=self.project_list.yview
        )

        self.project_list.config(
            yscrollcommand=scrollbar.set
        )

        self.project_list.pack(
            side="left",
            fill="both",
            expand=True
        )

        scrollbar.pack(
            side="right",
            fill="y"
        )

        self.project_list.bind(
            "<Double-Button-1>",
            self.project_clicked
        )

        # ---------------- RIGHT TITLE ----------------

        self.file_title = tk.Label(
            right_frame,
            text="Files",
            bg="white",
            fg="#222222",
            anchor="w",
            font=("Segoe UI", 11, "bold"),
            padx=10,
            pady=8
        )

        self.file_title.pack(fill="x")

        # ---------------- FILE LIST ----------------

        file_frame = tk.Frame(
            right_frame,
            bg="white"
        )

        file_frame.pack(
            fill="both",
            expand=True
        )

        self.file_tree = ttk.Treeview(
            file_frame,
            columns=("type", "path"),
            show="tree headings"
        )

        self.file_tree.heading(
            "#0",
            text="File"
        )

        self.file_tree.heading(
            "type",
            text="Type"
        )

        self.file_tree.heading(
            "path",
            text="Location"
        )

        self.file_tree.column(
            "#0",
            width=250
        )

        self.file_tree.column(
            "type",
            width=100
        )

        self.file_tree.column(
            "path",
            width=350
        )

        tree_scroll = tk.Scrollbar(
            file_frame,
            command=self.file_tree.yview
        )

        self.file_tree.config(
            yscrollcommand=tree_scroll.set
        )

        self.file_tree.pack(
            side="left",
            fill="both",
            expand=True
        )

        tree_scroll.pack(
            side="right",
            fill="y"
        )

        self.file_tree.bind(
            "<Double-Button-1>",
            self.file_clicked
        )

        # ---------------- STATUS BAR ----------------

        self.status = tk.Label(
            self.root,
            text="Enter a directory path and press GO",
            bg="#eeeeee",
            fg="#666666",
            anchor="w",
            padx=10,
            pady=5
        )

        self.status.pack(
            fill="x",
            side="bottom"
        )

    # -----------------------------------------------------
    # GO TO PATH
    # -----------------------------------------------------

    def go_path(self):

        path_text = self.path_entry.get().strip()

        if not path_text:
            return

        path = Path(path_text).expanduser()

        if not path.exists():
            messagebox.showerror(
                "Invalid Path",
                "The specified path does not exist."
            )
            return

        if not path.is_dir():
            messagebox.showerror(
                "Invalid Path",
                "Please enter a directory."
            )
            return

        self.history.clear()

        self.current_path = path
        self.project_root = path

        self.show_projects(path)

    # -----------------------------------------------------
    # SHOW ANDROID PROJECTS
    # -----------------------------------------------------

    def show_projects(self, path):

        self.project_list.delete(0, tk.END)

        self.file_tree.delete(
            *self.file_tree.get_children()
        )

        self.left_title.config(
            text="Android Projects"
        )

        projects = []

        try:

            for item in path.iterdir():

                if not item.is_dir():
                    continue

                if item.name in IGNORED_DIRS:
                    continue

                if self.is_android_project(item):
                    projects.append(item)

        except PermissionError:
            messagebox.showerror(
                "Permission Error",
                "Cannot access this directory."
            )
            return

        projects.sort(
            key=lambda x: x.name.lower()
        )

        for project in projects:

            self.project_list.insert(
                tk.END,
                project.name
            )

        self.status.config(
            text=f"{len(projects)} Android project(s) found"
        )

    # -----------------------------------------------------
    # DETECT ANDROID PROJECT
    # -----------------------------------------------------

    def is_android_project(self, path):

        # Typical Android project indicators

        indicators = [
            "settings.gradle",
            "settings.gradle.kts",
            "build.gradle",
            "build.gradle.kts"
        ]

        for file in indicators:

            if (path / file).exists():
                return True

        # Sometimes the Android module is directly present

        if (path / "app").is_dir():

            app = path / "app"

            if (
                (app / "src").exists()
                or
                (app / "build.gradle").exists()
                or
                (app / "build.gradle.kts").exists()
            ):
                return True

        return False

    # -----------------------------------------------------
    # PROJECT CLICK
    # -----------------------------------------------------

    def project_clicked(self, event):

        selection = self.project_list.curselection()

        if not selection:
            return

        project_name = self.project_list.get(
            selection[0]
        )

        # Always build the project path from the
        # directory containing all projects
        project_path = self.project_root / project_name

        if not project_path.exists():
            messagebox.showerror(
                "Error",
                "Project no longer exists."
            )
            return

        self.history.append(
            self.current_path
        )

        self.current_path = project_path

        self.path_entry.delete(0, tk.END)
        self.path_entry.insert(
            0,
            str(project_path)
        )

        self.show_project_files(
            project_path
    )
    # -----------------------------------------------------
    # SHOW IMPORTANT PROJECT FILES
    # -----------------------------------------------------

    def show_project_files(self, project):

        self.file_tree.delete(
            *self.file_tree.get_children()
        )

        self.file_title.config(
            text=f"Files — {project.name}"
        )

        # Search important locations

        locations = [
            project / "app" / "src" / "main" / "java",
            project / "app" / "src" / "main" / "kotlin",
            project / "app" / "src" / "main" / "res"
        ]

        for location in locations:

            if location.exists():

                self.add_directory(
                    location,
                    project
                )

        self.status.config(
            text=f"Browsing {project.name}"
        )

    # -----------------------------------------------------
    # ADD DIRECTORY TO TREE
    # -----------------------------------------------------

    def add_directory(self, directory, project):

        try:
            files = list(directory.rglob("*"))

        except PermissionError:
            return

        for file in files:

            if not file.is_file():
                continue

            # Ignore unwanted directories

            if any(
                ignored in file.parts
                for ignored in IGNORED_DIRS
            ):
                continue

            if not self.is_important_file(file):
                continue

            relative_path = file.relative_to(project)

            file_type = self.get_file_type(
                file
            )

            self.file_tree.insert(
                "",
                "end",
                text=file.name,
                values=(
                    file_type,
                    str(relative_path)
                ),
                tags=(str(file),)
            )

    # -----------------------------------------------------
    # FILE FILTER
    # -----------------------------------------------------

    def is_important_file(self, file):

        extension = file.suffix.lower()

        # Kotlin / Java
        if extension in {".kt", ".java"}:
            return True

        # XML only if inside res
        if extension == ".xml":

            if "res" in file.parts:

                # Only useful Android resource directories
                useful_dirs = {
                    "layout",
                    "layout-land",
                    "layout-sw600dp",
                    "drawable",
                    "drawable-hdpi",
                    "drawable-mdpi",
                    "drawable-xhdpi",
                    "drawable-xxhdpi",
                    "drawable-xxxhdpi",
                    "mipmap",
                    "mipmap-hdpi",
                    "mipmap-mdpi",
                    "mipmap-xhdpi",
                    "mipmap-xxhdpi",
                    "mipmap-xxxhdpi",
                    "values",
                    "values-night",
                    "menu",
                    "xml"
                }

                return any(
                    directory in file.parts
                    for directory in useful_dirs
                )

        return False

    # -----------------------------------------------------
    # FILE TYPE
    # -----------------------------------------------------

    def get_file_type(self, file):

        extension = file.suffix.lower()

        if extension == ".kt":
            return "Kotlin"

        if extension == ".java":
            return "Java"

        if extension == ".xml":

            if "layout" in file.parts:
                return "Layout XML"

            if any(
                part.startswith("drawable")
                for part in file.parts
            ):
                return "Drawable XML"

            if "values" in file.parts:
                return "Values XML"

            return "XML"

        return "File"

    # -----------------------------------------------------
    # FILE CLICK
    # -----------------------------------------------------

    def file_clicked(self, event):

        selection = self.file_tree.selection()

        if not selection:
            return

        item = selection[0]

        tags = self.file_tree.item(
            item,
            "tags"
        )

        if not tags:
            return

        file_path = Path(tags[0])

        if not file_path.exists():
            messagebox.showerror(
                "Error",
                "File no longer exists."
            )
            return

        self.open_file(file_path)

    # -----------------------------------------------------
    # OPEN CODE VIEWER
    # -----------------------------------------------------

    def open_file(self, file_path):

        try:

            content = file_path.read_text(
                encoding="utf-8"
            )

        except UnicodeDecodeError:

            try:
                content = file_path.read_text(
                    encoding="utf-8",
                    errors="replace"
                )

            except Exception as e:

                messagebox.showerror(
                    "Error",
                    str(e)
                )

                return

        except Exception as e:

            messagebox.showerror(
                "Error",
                str(e)
            )

            return

        viewer = tk.Toplevel(
            self.root
        )

        viewer.title(
            file_path.name
        )

        viewer.geometry(
            "1000x700"
        )

        # File path at top

        path_label = tk.Label(
            viewer,
            text=str(file_path),
            bg="#f5f5f5",
            fg="#666666",
            anchor="w",
            padx=10,
            pady=8,
            font=("Segoe UI", 9)
        )

        path_label.pack(
            fill="x"
        )

        text_frame = tk.Frame(
            viewer,
            bg="#f5f5f5"
        )

        text_frame.pack(
            fill="both",
            expand=True
        )

        text = tk.Text(
            text_frame,
            bg="white",
            fg="#222222",
            insertbackground="#222222",
            font=("Consolas", 11),
            undo=False,
            wrap="none"
        )

        vertical_scroll = tk.Scrollbar(
            text_frame,
            command=text.yview
        )

        horizontal_scroll = tk.Scrollbar(
            text_frame,
            command=text.xview,
            orient="horizontal"
        )

        text.config(
            yscrollcommand=vertical_scroll.set,
            xscrollcommand=horizontal_scroll.set
        )

        vertical_scroll.pack(
            side="right",
            fill="y"
        )

        horizontal_scroll.pack(
            side="bottom",
            fill="x"
        )

        text.pack(
            fill="both",
            expand=True
        )

        text.insert(
            "1.0",
            content
        )

        text.config(
            state="disabled"
        )

    # -----------------------------------------------------
    # BACK
    # -----------------------------------------------------
    def go_back(self):

        if not self.history:
            return

        self.current_path = self.history.pop()
        self.project_root = self.current_path

        self.path_entry.delete(
            0,
            tk.END
        )

        self.path_entry.insert(
            0,
            str(self.current_path)
        )

        self.show_projects(
            self.current_path
        )



# ---------------------------------------------------------
# RUN
# ---------------------------------------------------------

if __name__ == "__main__":

    root = tk.Tk()

    root.configure(
        bg="#f5f5f5"
    )

    app = AndroidBrowser(root)

    root.mainloop()